package Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kavireletronic.ali.gortkehgame.GameActivity;
import com.kavireletronic.ali.gortkehgame.R;
import com.valdesekamdem.library.mdtoast.MDToast;
import static maes.tech.intentanim.CustomIntent.customType;

import java.util.List;

import Model.LevelModel;
import Utils.FormatHelper;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>  {
    private List<LevelModel> levelModelList;
    private Context context;
    private SharedPreferences SP;

    public ListAdapter(List<LevelModel> levelModelList, Context context) {
        this.levelModelList = levelModelList;
        this.context = context;

        SP = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ListAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        LevelModel levelModel=levelModelList.get(position);

        holder.name.setText(FormatHelper.toPersianNumber("مرحله:"+levelModel.getName()));
        holder.argam.setText(levelModel.getArgame());
        holder.ahdad.setText(levelModel.getAhdad());
        holder.level.setText(levelModel.getLevel());
        holder.mar.setText(levelModel.getName());

        if (SP.getInt("level_user",1)>=Integer.parseInt(levelModel.getName())){
            holder.icon.setImageResource(R.drawable.lock_open);
        }else {
            holder.icon.setImageResource(R.drawable.lock_close);
        }

    }

    @Override
    public int getItemCount() {
        return levelModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView mar,level,ahdad,argam,name;
        public Holder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.lock_icon);
            mar=(TextView)itemView.findViewById(R.id.mar);
            level=(TextView)itemView.findViewById(R.id.level);
            ahdad=(TextView)itemView.findViewById(R.id.ahdad);
            argam=(TextView)itemView.findViewById(R.id.argam);
            name=(TextView)itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId()==itemView.getId()){
                TextView mar=(TextView)view.findViewById(R.id.mar);
                TextView level=(TextView)view.findViewById(R.id.level);
                TextView ahdad=(TextView)view.findViewById(R.id.ahdad);
                TextView argam=(TextView)view.findViewById(R.id.argam);

                String mar_=mar.getText().toString();
                String level_=level.getText().toString();
                String ahdad_=ahdad.getText().toString();
                String argam_=argam.getText().toString();

                Intent intent=new Intent(context, GameActivity.class);
                intent.putExtra("mar",mar_);
                intent.putExtra("level",level_);
                intent.putExtra("ahdad",ahdad_);
                intent.putExtra("argam",argam_);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (SP.getInt("level_user",1)>=Integer.parseInt(mar_)){
                    context.startActivity(intent);
//                    customType(context,"right-to-left");
                }else {
                    MDToast mdToast= MDToast.makeText(context,context.getString(R.string.not_access),MDToast.LENGTH_LONG,MDToast.TYPE_WARNING);
                    mdToast.show();
                }


            }
        }
    }




}
