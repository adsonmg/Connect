package app.connect.com.connect;

import android.view.View;

/**
 * Created by adson 2/12/2016
 */
public interface RecyclerViewOnClickListener {
     void onClickListener(View view, int position);
     void onLongPressClickListener(View view, int position);
}
