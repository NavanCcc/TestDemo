package com.example.chenren1.hello;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chenren1.hello.activity.Activity1;
import com.example.chenren1.hello.activity.Activity2;
import com.example.chenren1.hello.receiver.AlarmReceiver;
import com.example.chenren1.hello.receiver.ConnectivityReceiver;
import com.example.chenren1.hello.receiver.InstallApkReceiver;
import com.example.chenren1.hello.receiver.ScreenReceiver;
import com.example.chenren1.hello.service.TestService1;
import com.example.chenren1.hello.utils.CpuUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sina.testaar.TestAAR;

public class MainActivity extends Activity {

    private ConnectivityReceiver connectivityReceiver;
    private InstallApkReceiver installApkReceiver;
    private ScreenReceiver screenReceiver;
    private AlarmReceiver alarmReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(MainActivity.this, Activity1.class));
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestService1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startService(intent);
            }
        });

        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGuardPushService();
            }
        });
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGuardPushForegroundServices();
            }
        });
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSchemeCallSinaNews();
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDateTest();
            }
        });

        findViewById(R.id.btn_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schemeCall(true);
            }
        });

        findViewById(R.id.btn_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schemeCall(false);
            }
        });

        findViewById(R.id.btn_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(MainActivity.this, Activity2.class));
            }
        });

        findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDirectSchemeCall();
            }
        });

        findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSinaDynmActivity();
            }
        });


        findViewById(R.id.btn_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSinaDynmBActivity();
            }
        });

        findViewById(R.id.btn_14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSinaActivity();
            }
        });

        registerReceivers();

        getCpuInfo();

        testCollection();

        getInstallTime();

        TestAAR.printMsg("Test_AAR");
    }

    private void startSinaDynmActivity() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sina.news","com.sina.news.module.push.guard.activity.SinaDynmActivity"));
        startActivity(intent);
    }

    private void startSinaDynmBActivity() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sina.news","com.sina.news.module.push.guard.activity.SinaDynmBActivity"));
        startActivity(intent);
    }

    private void startSinaActivity() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sina.news","com.sina.news.ui.MainActivity"));
        startActivity(intent);
    }

    private void schemeCall(boolean isWeibo) {
        Intent intent;
        if (isWeibo) {
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("sinanews://?::url=https://weibo.com/6250824982/GFfHBkKJ2::params={}"));
        } else {
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("sinanews://?::url=https://www.baidu.com/::params={}"));
        }
        startActivity(intent);
    }

    public boolean getInstallTime() {
        boolean isNew = false;

        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            if (packageInfo != null) {
                Log.e(CpuUtils.TAG, "time firstInstallTime：" + packageInfo.firstInstallTime);
                Log.e(CpuUtils.TAG, "time  lastUpdateTime：" + packageInfo.lastUpdateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNew;
    }


    private void doDateTest() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String lastDateStr = "20018-8-10";

        try {
            Date lastDate = format.parse(lastDateStr);
            Date currentDate = new Date();

            Log.e(CpuUtils.TAG, "两个日期的差距：" + differentDays(lastDate, currentDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    private int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            // 不同年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            // 同一年
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }


    private void startSchemeCallSinaNews() {
        String uriString = "sinanews://params={\"id\":\"hhtfwqr4367623-comos-video-cms\",\"type\":\"\",\"isSilence\":\"0\",\"skipAd\":\"0\"}::" +
                "k=sinawap*zx*zx*wm3049_0015_LAND_hhtfwqr4414732_uid5910207550*SN_0108001002*1534322847444*https%3A%2F%2Fzx.sina.cn%2Fe%2F2018-08" +
                "-15%2Fzx-ihhtfwqr4414732.d.html%3FHTTPS%3D1%26wm%3D3049_0015%26hd%3D1*ustat___61.135.152.137_1534304371_0.87417600_end::ustat=__" +
                "61.135.152.137_1534304371_0.87417600::opid=15343228474572813406";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void startDirectSchemeCall() {
        String uriString = "sinanewsdirect://params={\"id\":\"hhtfwqr4367623-comos-video-cms\",\"type\":\"\",\"isSilence\":\"0\",\"skipAd\":\"0\"}";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void testCollection() {

        Random random = new Random();
        List<Integer> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add(random.nextInt(1000));
        }
        Log.e(CpuUtils.TAG, "cccc Collections.sort " + listData.toString());
        Collections.sort(listData);
        Log.e(CpuUtils.TAG, "cccc listData " + listData.toString());
//        ArrayList.sort(listData);
        Log.e(CpuUtils.TAG, "cccc listData " + listData.toString());

    }


    private void getCpuInfo() {
        Log.e(CpuUtils.TAG, "cccc checkIfCPUx86 " + CpuUtils.checkIfCPUx86());

        Log.e(CpuUtils.TAG, "cccc getArchType " + CpuUtils.getArchType(this));

    }

    private void startGuardPushService() {
        Log.e(CpuUtils.TAG, "cccc startGuardPushService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sina.news", "com.sina.news.push.GuardPushService"));
        startService(intent);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startGuardPushForegroundServices() {
        Log.e(CpuUtils.TAG, "cccc startGuardPushForegroundServices");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sina.news", "com.sina.news.push.GuardPushService"));
        startForegroundService(intent);
    }

    private void startTestService1() {
        Intent intent = new Intent(this, TestService1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startService(intent);
    }

    private void registerReceivers() {
        Log.e(CpuUtils.TAG, "cccc registerReceivers ");
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        connectivityReceiver = new ConnectivityReceiver();
        this.registerReceiver(connectivityReceiver, filter);

        IntentFilter filter1 = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        installApkReceiver = new InstallApkReceiver();
        this.registerReceiver(installApkReceiver, filter1);

        IntentFilter filter2 = new IntentFilter(ScreenReceiver.USER_PRESENT_ACTION);
        screenReceiver = new ScreenReceiver();
        this.registerReceiver(screenReceiver, filter2);

        IntentFilter filter3 = new IntentFilter();
        filter.addAction(AlarmReceiver.ALARM_ALER);
        filter.addAction(AlarmReceiver.ALARM_DONE);
        alarmReceiver = new AlarmReceiver();
        this.registerReceiver(alarmReceiver, filter3);
    }

    private void unRegisterReceivers() {
        Log.e(CpuUtils.TAG, "cccc unRegisterReceivers ");
        this.unregisterReceiver(connectivityReceiver);
        this.unregisterReceiver(installApkReceiver);
        this.unregisterReceiver(screenReceiver);
        this.unregisterReceiver(alarmReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterReceivers();
    }

    public String getString(String name) {
        String str = MainActivity.this.getResources().getString(getStringId(name));
        str = str == null ? "" : str;
        return str;
    }

    public int getStringId(String name) {
        return MainActivity.this.getResources().getIdentifier(name, "string", MainActivity.this.getPackageName());
    }
}

