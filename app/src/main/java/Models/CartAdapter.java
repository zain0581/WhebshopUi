package Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.webshpoui.R;

public class CartAdapter extends ArrayAdapter<Items> {

    public CartAdapter(Context context, int resource, List<Items> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item_row, parent, false);
        }

        Items item = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.cart_item_name);
        TextView priceTextView = convertView.findViewById(R.id.cart_item_price);

        nameTextView.setText(item.getName());
        priceTextView.setText("Price: $" + item.getPrice());

        return convertView;
    }
}
