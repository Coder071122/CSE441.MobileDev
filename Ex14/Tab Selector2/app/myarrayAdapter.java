package c
import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import

import java.util.ArrayList;
import java.util.List;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int layoutId;

    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item> a) {
        super(context, LayoutId, a);
        this.context = context;
        this.layoutId = LayoutId;
        this.myArray = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);
        }
        Item item = myArray.get(position);
        ImageView btnLike = convertView.findViewById(R.id.btn_like);
        int like = item.getLike();
        if (like == 1) {
            btnLike.setImageResource(R.drawable.ic_heart);
        } else {
            btnLike.setImageResource(R.drawable.ic_unheart);
        }
        TextView title = convertView.findViewById(R.id.txt_title);
        title.setText(item.getTitle());
        TextView code = convertView.findViewById(R.id.txt_code);
        code.setText(item.getId());
        return convertView;
    }

}
