package Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.webshpoui.AboutActivity;
import com.example.webshpoui.MainActivity;
import com.example.webshpoui.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VersionAdapter extends ArrayAdapter<Items>{

    Context context;
    List<Items> list = new ArrayList<>();


    public VersionAdapter(@NonNull Context context, int resource, List<Items> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        list.addAll(list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        VersionHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row, null);

            holder = new VersionHolder();
            holder.checkBox = convertView.findViewById(R.id.check_box);
            holder.textView = convertView.findViewById(R.id.tv_name);

            // Make sure the CheckBox is non-focusable so that the list item can still be clicked
            holder.checkBox.setFocusable(false);
            holder.checkBox.setFocusableInTouchMode(false);

            convertView.setTag(holder);

        } else {
            holder = (VersionHolder) convertView.getTag();
        }

        // Get the current item from the list
        Items item = list.get(position);

        // Set the item name in the TextView
        holder.textView.setText(item.getName());

        // Set a click listener for the convertView (list item)
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display a Toast when an item is clicked
                Toast.makeText(context, "Clicked: " + item.getName(), Toast.LENGTH_SHORT).show();

                // Create an Intent to navigate to AboutActivity
                Intent intent = new Intent(context, AboutActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("qty", item.getQty());
                intent.putExtra("category", item.getCategory());

                // Start the AboutActivity
                context.startActivity(intent);
            }
        });

        // Optionally, update the checkbox state based on some item property (if needed)
        // holder.checkBox.setChecked(item.isSelected());

        return convertView;
    }



    public static class VersionHolder{
        public CheckBox checkBox;
        public TextView textView;
    }
}
