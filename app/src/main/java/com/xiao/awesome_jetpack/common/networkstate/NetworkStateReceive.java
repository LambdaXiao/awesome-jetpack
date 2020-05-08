package com.xiao.awesome_jetpack.common.networkstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.utils.NetworkUtils;

import java.util.Objects;

/**
 * 描述：监听网络状态的广播接收者
 */
public class NetworkStateReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)){
            if(!NetworkUtils.isConnected()){
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show();

                // .....
            }
        }
    }
}
