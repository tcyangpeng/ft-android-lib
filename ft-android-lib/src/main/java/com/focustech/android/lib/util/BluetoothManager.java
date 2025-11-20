package com.focustech.android.lib.util;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.Set;

/**
 * <蓝牙管理工具类>
 * 提供蓝牙开关、扫描、配对等功能
 *
 * @author focustech
 * @version [版本号, 2024-11-20]
 * @see [相关类/方法]
 * @since [V1]
 */
public class BluetoothManager {

    private Context context;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothScanCallback scanCallback;
    private BroadcastReceiver scanReceiver;

    /**
     * 蓝牙扫描回调接口
     */
    public interface BluetoothScanCallback {
        /**
         * 发现新设备
         *
         * @param device 蓝牙设备
         */
        void onDeviceFound(BluetoothDevice device);

        /**
         * 扫描完成
         */
        void onScanFinished();

        /**
         * 扫描失败
         *
         * @param errorMsg 错误信息
         */
        void onScanFailed(String errorMsg);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public BluetoothManager(Context context) {
        this.context = context.getApplicationContext();
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 检查设备是否支持蓝牙
     *
     * @return true表示支持，false表示不支持
     */
    public boolean isBluetoothSupported() {
        return bluetoothAdapter != null;
    }

    /**
     * 检查蓝牙是否已开启
     *
     * @return true表示已开启，false表示未开启
     */
    public boolean isBluetoothEnabled() {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    /**
     * 开启蓝牙
     *
     * @return true表示成功，false表示失败
     */
    public boolean enableBluetooth() {
        if (bluetoothAdapter == null) {
            return false;
        }
        if (!bluetoothAdapter.isEnabled()) {
            return bluetoothAdapter.enable();
        }
        return true;
    }

    /**
     * 关闭蓝牙
     *
     * @return true表示成功，false表示失败
     */
    public boolean disableBluetooth() {
        if (bluetoothAdapter == null) {
            return false;
        }
        if (bluetoothAdapter.isEnabled()) {
            return bluetoothAdapter.disable();
        }
        return true;
    }

    /**
     * 请求开启蓝牙（需要用户确认）
     *
     * @return Intent对象，需要在Activity中使用startActivityForResult启动
     */
    public Intent getEnableBluetoothIntent() {
        return new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    }

    /**
     * 检查是否有蓝牙权限
     *
     * @return true表示有权限，false表示无权限
     */
    public boolean hasBluetoothPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Android 12及以上需要BLUETOOTH_SCAN和BLUETOOTH_CONNECT权限
            return ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT)
                            == PackageManager.PERMISSION_GRANTED;
        } else {
            // Android 12以下需要BLUETOOTH和ACCESS_FINE_LOCATION权限
            return ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED;
        }
    }

    /**
     * 获取已配对的设备列表
     *
     * @return 已配对设备集合，如果蓝牙不可用则返回null
     */
    public Set<BluetoothDevice> getPairedDevices() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return null;
        }
        try {
            return bluetoothAdapter.getBondedDevices();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 开始扫描蓝牙设备
     *
     * @param callback 扫描回调
     * @return true表示成功开始扫描，false表示失败
     */
    public boolean startScan(final BluetoothScanCallback callback) {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            if (callback != null) {
                callback.onScanFailed("蓝牙未开启或不可用");
            }
            return false;
        }

        if (!hasBluetoothPermission()) {
            if (callback != null) {
                callback.onScanFailed("缺少蓝牙权限");
            }
            return false;
        }

        this.scanCallback = callback;

        // 注册广播接收器
        scanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device != null && scanCallback != null) {
                        scanCallback.onDeviceFound(device);
                    }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    if (scanCallback != null) {
                        scanCallback.onScanFinished();
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(scanReceiver, filter);

        try {
            return bluetoothAdapter.startDiscovery();
        } catch (SecurityException e) {
            e.printStackTrace();
            if (callback != null) {
                callback.onScanFailed("权限不足: " + e.getMessage());
            }
            return false;
        }
    }

    /**
     * 停止扫描蓝牙设备
     */
    public void stopScan() {
        if (bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
            try {
                bluetoothAdapter.cancelDiscovery();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        if (scanReceiver != null) {
            try {
                context.unregisterReceiver(scanReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanReceiver = null;
        }
        scanCallback = null;
    }

    /**
     * 判断是否正在扫描
     *
     * @return true表示正在扫描，false表示未扫描
     */
    public boolean isScanning() {
        return bluetoothAdapter != null && bluetoothAdapter.isDiscovering();
    }

    /**
     * 使设备可被其他蓝牙设备发现
     *
     * @param duration 可发现时长（秒），最大300秒
     * @return Intent对象，需要在Activity中使用startActivityForResult启动
     */
    public Intent getDiscoverableIntent(int duration) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, Math.min(duration, 300));
        return intent;
    }

    /**
     * 获取本地蓝牙设备名称
     *
     * @return 设备名称，如果蓝牙不可用则返回null
     */
    public String getLocalDeviceName() {
        if (bluetoothAdapter == null) {
            return null;
        }
        try {
            return bluetoothAdapter.getName();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置本地蓝牙设备名称
     *
     * @param name 设备名称
     * @return true表示成功，false表示失败
     */
    public boolean setLocalDeviceName(String name) {
        if (bluetoothAdapter == null || GeneralUtils.isNullOrEmpty(name)) {
            return false;
        }
        try {
            return bluetoothAdapter.setName(name);
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取本地蓝牙MAC地址
     *
     * @return MAC地址，如果蓝牙不可用则返回null
     */
    public String getLocalDeviceAddress() {
        if (bluetoothAdapter == null) {
            return null;
        }
        try {
            return bluetoothAdapter.getAddress();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        stopScan();
    }
}
