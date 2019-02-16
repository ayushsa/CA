package com.tact.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tact.activity.VoterDetailActivity;
import com.tact.kumbhca.R;
import com.tact.model.VoterData;

import java.util.ArrayList;

public class UnsurveyedDataAdapter extends RecyclerView.Adapter<UnsurveyedDataAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<VoterData> voterList;
    private LayoutInflater inflater;

    public UnsurveyedDataAdapter(Context context, ArrayList<VoterData> voterList) {
        this.context = context;
        this.voterList = voterList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_unservyed_data, parent, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final VoterData voterData = voterList.get(position);


        holder.tv_prefix.setText(voterData.getPrefix());
        holder.tv_ac_no.setText(voterData.getAcNo() + "");
        holder.tv_ac_name.setText(voterData.getAcName());
        holder.tv_psNo.setText(voterData.getPsNo() + "");
        holder.tv_ps_name.setText(voterData.getPsName());
        holder.tv_section_no.setText(voterData.getSectionNo() + "");
        holder.tv_voter_id.setText(voterData.getVoterId());
        holder.tv_section_name.setText(voterData.getSectionName());
        holder.tv_voter_ser_id.setText(voterData.getVoterSerial() + "");
        holder.tv_name.setText(voterData.getName());
        holder.tv_gender.setText(voterData.getGender());
        holder.tv_father_name.setText(voterData.getFatherName());
        holder.tv_age.setText(voterData.getAge() + "");
        holder.tv_dob.setText((String) voterData.getDob());
        holder.tv_mobile_no.setText((String) voterData.getMobileNo());
        holder.tv_house_no.setText(voterData.getHouseNo());


        if(position %2 == 1)
        {
            holder.llParent.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.llParent.setBackgroundColor(Color.parseColor("#ffebee"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        holder.cardViewCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VoterDetailActivity.class);
                intent.putExtra("VoterData", voterData);
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return voterList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_prefix;
        TextView tv_ac_no;
        TextView tv_ac_name;
        TextView tv_psNo;
        TextView tv_ps_name;
        TextView tv_section_no;
        TextView tv_voter_id;
        TextView tv_section_name;
        TextView tv_voter_ser_id;
        TextView tv_name;
        TextView tv_gender;
        TextView tv_father_name;
        TextView tv_age;
        TextView tv_dob;
        TextView tv_mobile_no;
        TextView tv_house_no;
        CardView cardViewCitizen;
        LinearLayout llParent;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tv_prefix = itemView.findViewById(R.id.tv_prefix);
            tv_ac_no = itemView.findViewById(R.id.tv_ac_no);
            tv_ac_name = itemView.findViewById(R.id.tv_ac_name);
            tv_psNo = itemView.findViewById(R.id.tv_psNo);
            tv_ps_name = itemView.findViewById(R.id.tv_ps_name);
            tv_section_no = itemView.findViewById(R.id.tv_section_no);
            tv_voter_id = itemView.findViewById(R.id.tv_voter_id);
            tv_voter_ser_id = itemView.findViewById(R.id.tv_voter_ser_id);
            tv_section_name = itemView.findViewById(R.id.tv_section_name);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_father_name = itemView.findViewById(R.id.tv_father_name);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_dob = itemView.findViewById(R.id.tv_dob);
            tv_mobile_no = itemView.findViewById(R.id.tv_mobile_no);
            tv_house_no = itemView.findViewById(R.id.tv_house_no);
            cardViewCitizen = itemView.findViewById(R.id.cardViewCitizen);
            llParent = itemView.findViewById(R.id.llParent);
        }
    }
}
