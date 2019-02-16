package com.tact.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tact.kumbhca.R;
import com.tact.model.VoterData;

import java.util.ArrayList;

public class SurveyedDataAdapter extends RecyclerView.Adapter<SurveyedDataAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<VoterData> voterList;
    private LayoutInflater inflater;

    public SurveyedDataAdapter(Context context, ArrayList<VoterData> voterList)
    {
        this.context = context;
        this.voterList = voterList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.row_servyed_data, parent, false);
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
        holder.tv_house_no.setText(voterData.getHouseNo());

        holder.llSurveyedData.setVisibility(View.VISIBLE);
        holder.tvMobileNumber1.setText(voterData.getMobileNo());
        holder.tvMobileNumber2.setText(voterData.getMobileNo2());

        if (voterData.getPriority().equals("1"))
        {
            holder.tvPriority.setText("Very Important");
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.material_gree_n));
        }
        else if (voterData.getPriority().equals("2"))
        {
            holder.tvPriority.setText("Important");
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.material_orange));
        }
        else if (voterData.getPriority().equals("3"))
        {
            holder.tvPriority.setText("As Usual");
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.material_yellow));
        }
        else if (voterData.getPriority().equals("4"))
        {
            holder.tvPriority.setText("Not Interested");
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.white));
        }





//        if (voterData.getMobileNo() != null && voterData.getMobileNo().isEmpty() && voterData.getMobileNo().equals("null"))
//        {
//            holder.tvMobileNumber1.setText(voterData.getMobileNo()+"");
//        }
//        else
//        {
//            holder.tvMobileNumber1.setText("N/A");
//        }
//
//        if (voterData.getMobileNo2() != null && voterData.getMobileNo2().isEmpty() && voterData.getMobileNo2().equals("null"))
//        {
//            holder.tvMobileNumber2.setText(voterData.getMobileNo2()+"");
//        }
//        else
//        {
//            holder.tvMobileNumber2.setText("N/A");
//        }
//
//        if (voterData.getPriority() != null && voterData.getPriority().isEmpty() && voterData.getPriority().equals("null"))
//        {
//            holder.tvPriority.setText(voterData.getPriority()+"");
//        }
//        else
//        {
//            holder.tvPriority.setText("N/A");
//        }





//        if (voterData.getIsSynced() == 1) {
//            holder.llParent.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        } else {
//            holder.llParent.setBackgroundColor(Color.parseColor("#ffebee"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
//        }


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
        TextView tv_house_no;
        CardView cardViewCitizen;
        LinearLayout llParent;
        LinearLayout llSurveyedData;

        TextView tvMobileNumber1;
        TextView tvMobileNumber2;
        TextView tvPriority;

        public CustomViewHolder(View itemView)
        {
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
            tv_house_no = itemView.findViewById(R.id.tv_house_no);
            cardViewCitizen = itemView.findViewById(R.id.cardViewCitizen);
            llParent = itemView.findViewById(R.id.llParent);
            llSurveyedData = itemView.findViewById(R.id.llSurveyedData);

            tvMobileNumber1 = itemView.findViewById(R.id.tvMobileNumber1);
            tvMobileNumber2 = itemView.findViewById(R.id.tvMobileNumber2);
            tvPriority = itemView.findViewById(R.id.tvPriority);
        }
    }




}
