package com.yongchun.multiimageselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button selectPicture;
    private int maxSelectNum = 9; // 可选择最大图片数量
    private int mode = 1; //多选 =1  or 单选  = 2  模式
    private boolean isShow = false; //是否选择拍照选项
    private boolean isPreview = true; //是否选择预览选项
    private boolean isCrop = false; //是否选择剪切选项

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    public void initView() {
        selectPicture = (Button) findViewById(R.id.select_picture);
    }

    public void registerListener() {
        selectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelectorActivity.start(MainActivity.this, maxSelectNum, mode, isShow, isPreview, isCrop);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            startActivity(new Intent(this, SelectResultActivity.class).putExtra(SelectResultActivity.EXTRA_IMAGES, images));
        }
    }
}
