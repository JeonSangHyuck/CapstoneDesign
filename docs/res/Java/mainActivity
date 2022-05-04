package com.cookandroid.test0427;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    CalendarView calView;
    TextView tv, tv2, tv3;
    int selectYear, selectMonth, selectDay;
    String filename;


    EditText editText, editText2;
    View dialogView;

    static int income = 0;
    static int expense = 0;
    static int balance = 0;

    //하단 바 생성
    final String Tag = this.getClass().getSimpleName();

    LinearLayout month_ly;
    BottomNavigationView bottomNavigationView;

    //로그인 성공 시 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        Toast.makeText(getApplicationContext(), "사용자 이름 : " +name+ "님이 로그인하셨습니다.", Toast.LENGTH_SHORT).show();


        calView = findViewById(R.id.calendarView);
        tv = findViewById(R.id.tv_sum);
        tv2 = findViewById(R.id.tv_income);
        tv3 = findViewById(R.id.tv_spend);

        //SD카드에 READ, WRITE권한 주기
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        //SD카드 경로 지정
        final String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(sdpath + "/Account");
        myDir.mkdir();    //sd카드에 Account폴더 생성

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int datOfMonth) {
                selectYear = year;
                selectMonth = month + 1;    
                selectDay = datOfMonth;


                filename = Integer.toString(selectYear) + "년"
                        + Integer.toString(selectMonth) + "월"
                        + Integer.toString(selectDay) + "일";
                String path = sdpath + "/Account/" + filename;
                File files = new File(path);
                if (files.exists()) {    //파일이 존재하는 경우 읽어오기
                    try {
                        FileInputStream fin = new FileInputStream(path);
                        byte[] txt = new byte[100];
                        fin.read(txt);
                        String str = new String(txt);
                        AlertDialog.Builder readDlg = new AlertDialog.Builder(MainActivity.this);
                        readDlg.setTitle("가계부 읽기");
                        readDlg.setMessage(str);
                        readDlg.setIcon(R.drawable.testicon);
                        readDlg.setPositiveButton("확인", null);
                        readDlg.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {    //파일이 존재하지 않는 경우 파일 생성하기
                    dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    editText = dialogView.findViewById(R.id.editText);
                    editText2 = dialogView.findViewById(R.id.editText2);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("가계부 쓰기");
                    dlg.setView(dialogView);
                    dlg.setIcon(R.drawable.testicon);
                    dlg.setNegativeButton("취소", null);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                FileOutputStream fout = new FileOutputStream(path);
                                int etIncome = Integer.parseInt(editText.getText().toString());
                                int etExpense = Integer.parseInt(editText2.getText().toString());
                                income = income + etIncome;
                                expense = expense + etExpense;
                                balance = income - expense;

                                String str = "수입  " + income + "지출  " + expense + "\n"
                                        + "합계  " + balance;

                                String writeStr = "수입 : " + etIncome + "\n" + "지출 : " + etExpense;
                                fout.write(writeStr.getBytes());
                                fout.close();
                                tv.setText(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    dlg.show();
                }
            }
        });


        //액션바 설정하기//
        //액션바 타이틀 변경하기
        getSupportActionBar().setTitle("Saver");
        //액션바 배경색 변경
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //액션바 숨기기
        //hideActionBar();

        //하단바 설정하기//
        init(); //객체 정의
        SettingListener(); //리스너 등록

        //맨 처음 시작하는 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.month_ly);


    }

    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        
        if (id == android.R.id.home) {
            Toast.makeText(this, "홈아이콘 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(this, "검색 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //액션바 숨기기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }

    private void init(){
        month_ly = findViewById(R.id.month_ly);
        bottomNavigationView = findViewById(R.id.Bmenu);
    }
    private void SettingListener(){
        //선택 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
            switch(menuItem.getItemId()){
                case R.id.tab_m:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.month_ly, new Fragment_month())
                            .commit();

                    return true;
                }
                case R.id.tab_w:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.month_ly, new Fragment_week())
                            .commit();
                    return true;
                }
                case R.id.tab_d:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.month_ly, new Fragment_day())
                            .commit();
                    return true;
                }
                case R.id.tab_s:{
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.month_ly, new Fragment_settings())
                            .commit();
                    return true;
                }
            }
            return false;
        }
    }




}
