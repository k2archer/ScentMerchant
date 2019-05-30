package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.StartActivity;
import com.kwei.scentmerchant.bean.ShopBean;
import com.kwei.scentmerchant.contract.ShopContract;
import com.kwei.scentmerchant.presenter.ShopPresenterImpl;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ShopActivity extends AppCompatActivity implements ShopContract.View {

    @BindView(R.id.bt_submit)
    Button btSubmit;

    ShopBean shop;
    @BindView(R.id.ed_shop_name)
    EditText edShopName;
    @BindView(R.id.ed_shop_address)
    EditText edShopAddress;
    @BindView(R.id.sp_shop_food_type)
    AppCompatSpinner edShopFoodType;
    @BindView(R.id.ed_shop_table_amount)
    EditText edShopTableAmount;
    @BindView(R.id.ed_average_consumption)
    EditText edShopAverageConsumption;
    @BindView(R.id.ed_shop_exclusive_message)
    EditText edShopExclusiveMessage;

    @BindArray(R.array.food_types)
    String[] foodTypes;

    String mobile;
    ShopContract.ShopPresenter presenter;
    @BindView(R.id.ib_photo_1)
    AppCompatImageButton btPhoto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        shop = new ShopBean();
        mobile = getIntent().getStringExtra("login_mobile");

        presenter = new ShopPresenterImpl(this);
    }

    @OnItemSelected(R.id.sp_shop_food_type)
    public void onFoodTypeSelected(AdapterView<?> parent, View view, int position, long id) {
        if (shop != null) {
            shop.foodType = foodTypes[position];
        }
    }

    @OnClick({R.id.ib_photo_1, R.id.ib_photo_2, R.id.ib_photo_3})
    public void onImageButtonClicked(View v) {
        takePhoto(v.getId());
    }

    private final int CAMERA_RESULT = 1001;
    private int photoId = 0;
    private SparseArray<String> imageFiles = new SparseArray<>();

    private void takePhoto(int id) {
        photoId = id;
        String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + id + ".jpg";
        imageFiles.put(id, imageFilePath);
        Log.v("imageFilePath", imageFilePath);
        Uri imageUri = Uri.fromFile(new File(imageFilePath));

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // 指定文件存储位置
        startActivityForResult(i, CAMERA_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_RESULT) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                ImageButton imageButton = findViewById(photoId);
                imageButton.setImageBitmap(bitmap);
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = zipBitmap();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ImageButton imageButton = findViewById(photoId);
                                imageButton.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        }
    }

    private Bitmap zipBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        BitmapFactory.decodeFile(imageFiles.get(photoId), options);

        int imgWidth = options.outWidth; // 要加载的图片的宽
        int imgHeight = options.outHeight; // 要加载的图片的高
        int desiredWidth = 64;
        int desiredHeight = 64;
        int inSampleSize = 1;
        if (imgWidth > desiredWidth || imgHeight > desiredHeight) {
            int halfWidth = imgWidth / 2;
            int halfHeight = imgHeight / 2;

            while ((halfWidth / inSampleSize) >= desiredWidth &&
                    (halfHeight / inSampleSize) >= desiredHeight) {
                inSampleSize *= 2;
            }
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(imageFiles.get(photoId), options);
    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                if (!checkShopInfo()) {
                    break;
                }

                presenter.addShop();
                break;
        }
    }

    private boolean checkShopInfo() {
        if (edShopName.getText().length() == 0) {
            Toast.makeText(this, "店铺名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edShopAddress.getText().length() == 0) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edShopTableAmount.getText().length() == 0) {
            Toast.makeText(this, "桌位数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edShopAverageConsumption.getText().length() == 0) {
            Toast.makeText(this, "人均消费不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String[] getPictures() {
        int size = imageFiles.size();
        String[] files = new String[size];

        for (int i = 0; i < size; i++) {
            Log.d("TAG", "getPictures: " + imageFiles.valueAt(i));
            files[i] = imageFiles.valueAt(i);
        }

        return files;
    }


    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public String getShopName() {
        return edShopName.getText().toString();
    }

    @Override
    public String getShopAddress() {
        return edShopAddress.getText().toString();
    }

    @Override
    public String getShopFoodType() {
        return shop.foodType;
    }

    @Override
    public String getShopAverageConsumption() {
        return edShopAverageConsumption.getText().toString();
    }

    @Override
    public String getShopTableAmount() {
        return edShopTableAmount.getText().toString();
    }

    @Override
    public String[] getShopPictures() {
        return getPictures();
    }

    @Override
    public String getExclusiveMessage() {
        return edShopExclusiveMessage.getText().toString();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        setResult(StartActivity.ADD_SHOP_REQUEST_CODE, intent);
        finish();
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
