package in.ults.gisurvey.ui.survey.member;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.IPMSAutoCompleteEditText;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;
import static in.ults.gisurvey.utils.CommonUtils.disableWidget;
import static in.ults.gisurvey.utils.CommonUtils.enableWidget;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class MemberDetailsAdapter extends RecyclerView.Adapter<MemberDetailsAdapter.MyViewHolder> {

    private ArrayList<MemberDetailsItem> dataList;
    private BaseActivity activity;
    private boolean isValidation;
    private boolean isSurveyOpenEdit=true;



    class MyViewHolder extends RecyclerView.ViewHolder {
        static final int CHILD_AGE_LIMIT = 18;
        TextView memberTitle;
        in.ults.gisurvey.utils.IPMSEditText memberName;
        in.ults.gisurvey.utils.IPMSEditText memberAge;
        in.ults.gisurvey.utils.IPMSEditText memberAgeMonth;
        in.ults.gisurvey.utils.IPMSEditText memberDisabilityPercent;
        IPMSSpinner memberEducation;
        IPMSSpinner memberEducationType;
        IPMSAutoCompleteEditText memberJob;
        IPMSSpinner memberJobLoss;
        IPMSSpinner memberJobType;
        IPMSSpinner memberDisability;
        IPMSSpinner memberMartialStatus;
        IPMSSpinner memberGender;
        IPMSSpinner memberNRK;
        IPMSSpinner memberNRI;
        IPMSSpinner memberStudent;

        ImageView pensionAdd;
        ImageView pensionRemove;

        ImageView diseaseAdd;
        ImageView diseaseRemove;

        ConstraintLayout layoutContainer;

        TextInputLayout layoutMemberName;
        TextInputLayout layoutMemberAge;
        TextInputLayout layoutMemberAgeMonth;
        TextInputLayout layoutMemberDisabilityPercent;
        TextInputLayout layoutMemberEducation;
        TextInputLayout layoutMemberEducationType;
        TextInputLayout layoutMemberJob;
        TextInputLayout layoutMemberJobLoss;
        TextInputLayout layoutMemberJobType;
        TextInputLayout layoutMemberDisability;
        TextInputLayout layoutMemberMartialStatus;
        TextInputLayout layoutMemberNRK;
        TextInputLayout layoutMemberNRI;
        RecyclerView layoutMemberPension;
        RecyclerView layoutMemberDisease;
        TextInputLayout layoutMemberGender;
        TextInputLayout layoutMemberStudent;


        MyViewHolder(View view) {
            super(view);
            memberTitle = view.findViewById(R.id.txtMemberTitle);
            memberName = view.findViewById(R.id.etName);
            memberAge = view.findViewById(R.id.etAge);
            memberAgeMonth = view.findViewById(R.id.etAgeMonth);
            memberDisabilityPercent = view.findViewById(R.id.etDisabilityPercent);
            memberEducation = view.findViewById(R.id.etEducation);
            memberEducationType = view.findViewById(R.id.etEducationType);
            memberJob = view.findViewById(R.id.etJob);
            memberJobLoss = view.findViewById(R.id.etJobloss);
            memberJobType = view.findViewById(R.id.etJobType);
            memberDisability = view.findViewById(R.id.etDisability);
            memberMartialStatus = view.findViewById(R.id.etMartialStatus);
            memberGender = view.findViewById(R.id.etGender);
            memberNRI = view.findViewById(R.id.etIsNRI);
            memberNRK = view.findViewById(R.id.etIsNRK);
            memberStudent = view.findViewById(R.id.etStudent);

            pensionRemove = view.findViewById(R.id.btnPensionRemove);
            pensionAdd = view.findViewById(R.id.btnPensionAdd);

            diseaseAdd = view.findViewById(R.id.btnDiseaseAdd);
            diseaseRemove = view.findViewById(R.id.btnDiseaseRemove);

            layoutContainer=view.findViewById(R.id.layoutContainer);

            layoutMemberName = view.findViewById(R.id.layoutName);
            layoutMemberAge = view.findViewById(R.id.layoutAge);
            layoutMemberAgeMonth = view.findViewById(R.id.layoutAgeMonth);
            layoutMemberDisabilityPercent = view.findViewById(R.id.layoutDisabilityPercent);
            layoutMemberEducation = view.findViewById(R.id.layoutEducation);
            layoutMemberEducationType = view.findViewById(R.id.layoutEducationType);
            layoutMemberJob = view.findViewById(R.id.layoutJob);
            layoutMemberJobLoss = view.findViewById(R.id.layoutJobLoss);
            layoutMemberJobType = view.findViewById(R.id.layoutJobType);
            layoutMemberDisability = view.findViewById(R.id.layoutDisability);
            layoutMemberMartialStatus = view.findViewById(R.id.layoutMartialStatus);
            layoutMemberPension = view.findViewById(R.id.rvPension);
            layoutMemberDisease = view.findViewById(R.id.rvDisease);
            layoutMemberGender = view.findViewById(R.id.layoutGender);
            layoutMemberNRI = view.findViewById(R.id.layoutIsNRI);
            layoutMemberNRK = view.findViewById(R.id.layoutIsNRK);
            layoutMemberStudent = view.findViewById(R.id.layoutStudent);

            ArrayList<CommonItem> educations = IPMSApp.getAppInstance().getLocMainItem().getEducations();
            memberEducation.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, educations));
            memberEducation.setOnItemClickListener((parent, view1, position, id) -> {
                if (educations.get(position).getSubList() != null && educations.get(position).getSubList().size() > 0) {
                    memberEducationType.setText("");
                } else {
                    memberEducationType.setText("Nil");
                }
                memberEducationType.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, educations.get(position).getSubList()));
            });
            ArrayList<CommonItem> occupations = IPMSApp.getAppInstance().getLocMainItem().getOccupations();
            memberJobType.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, occupations));
            memberJobType.setOnItemClickListener((parent, view1, position, id) -> {
                if (occupations.get(position).getSubList() != null && occupations.get(position).getSubList().size() > 0) {
                    memberJob.setText("");
                } else {
                    memberJob.setText("Nil");
                }
                memberJob.setAdapter(new PostOfficeListAdapter(activity, R.layout.item_member_view, occupations.get(position).getSubList()));
            });
            memberJobLoss.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNONR()));
            memberGender.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getGender()));
            memberNRI.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNONR()));
            memberNRK.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNONR()));
            memberMartialStatus.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getMartialStatuses()));
            memberStudent.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNONR()));

            MemberPensionAdapter  pensionAdapter = new MemberPensionAdapter(activity);
            layoutMemberPension.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
            layoutMemberPension.setNestedScrollingEnabled(false);
            layoutMemberPension.setAdapter(pensionAdapter);
            memberDisability.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, IPMSApp.getAppInstance().getLocMainItem().getDisabilities()));


            MemberDiseaseAdapter  diseaseAdapter = new MemberDiseaseAdapter(activity);
            layoutMemberDisease.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
            layoutMemberDisease.setNestedScrollingEnabled(false);
            layoutMemberDisease.setAdapter(diseaseAdapter);

            diseaseAdd.setOnClickListener(v -> diseaseAdapter.addNewItem());
            diseaseRemove.setOnClickListener(v -> diseaseAdapter.removeItem());


            memberDisability.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String disability = memberDisability.getText().toString();
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setMemberDisability(memberDisability.getText().toString());
                    }

                    if (disability.equalsIgnoreCase(AppConstants.NO)) {
                        memberDisabilityPercent.setText("0");
                        memberDisabilityPercent.setEnabled(false);
                    } else if (disability.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                        memberDisabilityPercent.setText(AppConstants.NR_UPPERCASE);
                        memberDisabilityPercent.setEnabled(false);
                    } else {
                        memberDisabilityPercent.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            memberDisabilityPercent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String percent = Objects.requireNonNull(memberDisabilityPercent.getText()).toString();
                        if (!percent.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && percent.length() > 1 && Integer.parseInt(percent) > 100) {
                            memberDisabilityPercent.setText("");
                        }
                    }
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setMemberDisabilityPercent(Objects.requireNonNull(memberDisabilityPercent.getText()).toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            memberName.addTextChangedListener(new

                                                      TextWatcher() {
                                                          @Override
                                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                          }

                                                          @Override
                                                          public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                              if (dataList != null) {
                                                                  dataList.get(getAdapterPosition()).setMemberName(Objects.requireNonNull(memberName.getText()).toString());
                                                              }
                                                          }

                                                          @Override
                                                          public void afterTextChanged(Editable s) {

                                                          }
                                                      });
            memberAge.addTextChangedListener(new
//
                                                     TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (dataList != null) {
                        String ageMonth = Objects.requireNonNull(memberAgeMonth.getText()).toString();
                        String ageYear = Objects.requireNonNull(memberAge.getText()).toString();
                        if (ageMonth.length() == 0 && ageYear.length() == 0) {
                            dataList.get(getAdapterPosition()).setMemberAge("");
                            enableWidget(memberAgeMonth,layoutMemberAgeMonth);
                        } else if (ageYear.length() > 0) {
                            disableWidget(memberAgeMonth,layoutMemberAgeMonth);
                            if (!(dataList.get(getAdapterPosition()).getMemberAge().split(" ")[0]).equals(ageYear)) {
                                dataList.get(getAdapterPosition()).setMemberAge(ageYear + " " + activity.getString(R.string.member_year));
                                try {
                                    memberEducation.setText("");
                                    memberEducationType.setText("");
                                    memberStudent.setText("");
                                    if (Integer.valueOf(dataList.get(getAdapterPosition()).getMemberAge().split(" ")[0]) <= CHILD_AGE_LIMIT) {
                                        memberJob.setText(activity.getString(R.string.member_child_job));
                                        memberMartialStatus.setText(activity.getString(R.string.member_child_marital_status));
                                        memberJobLoss.setText(activity.getString(R.string.member_child_jobloss));
                                        memberJobType.setText(activity.getString(R.string.member_child_job));
                                        pensionAdapter.addChildPensionItem();
                                    } else {
                                        memberJob.setText("");
                                        memberMartialStatus.setText("");
                                        memberJobLoss.setText("");
                                        memberJobType.setText("");
                                        pensionAdapter.removeChildPensionItem();

                                    }
                                } catch (Exception e) {

                                }
                            }

                        }
                    }

                    if (s.length() > 0) {
                        int years = Integer.parseInt(String.valueOf(s));
                        if (years == 0) {
                            memberAge.setText("");
                        }
                        memberAgeMonth.setText("");
                    } else {

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            memberAgeMonth.addTextChangedListener(new

                                                          TextWatcher() {
                                                              @Override
                                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                              }

                                                              @Override
                                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                  if (dataList != null) {

                                                                      String ageMonth = Objects.requireNonNull(memberAgeMonth.getText()).toString();
                                                                      String ageYear = Objects.requireNonNull(memberAge.getText()).toString();
                                                                      if (ageMonth.length() == 0 && ageYear.length() == 0) {
                                                                          dataList.get(getAdapterPosition()).setMemberAge("");
                                                                          enableWidget(memberAge,layoutMemberAge);
                                                                      } else if (ageMonth.length() > 0) {
                                                                          disableWidget(memberAge,layoutMemberAge);
                                                                          dataList.get(getAdapterPosition()).setMemberAge(ageMonth + " " + activity.getString(R.string.member_month));
                                                                          try {

                                                                              memberJob.setText(activity.getString(R.string.member_child_job));
                                                                              memberMartialStatus.setText(activity.getString(R.string.member_child_marital_status));
                                                                              memberJobLoss.setText(activity.getString(R.string.member_child_jobloss));
                                                                              memberJobType.setText(activity.getString(R.string.member_child_job));
                                                                              pensionAdapter.addChildPensionItem();
                                                                              memberEducation.setText(activity.getString(R.string.member_child_education));
                                                                              memberEducationType.setText(activity.getString(R.string.member_child_education_type));
                                                                              memberStudent.setText(activity.getString(R.string.cmn_no));

                                                                          } catch (Exception e) {

                                                                          }

                                                                      }
                                                                  }

                                                                  if (s.length() > 0) {
                                                                      int month = Integer.parseInt(String.valueOf(s));
                                                                      if (month < 1 || month > 11) {
                                                                          memberAgeMonth.setText("");
                                                                      }
                                                                      memberAge.setText("");
                                                                  }
                                                              }

                                                              @Override
                                                              public void afterTextChanged(Editable s) {

                                                              }
                                                          });


            memberEducation.addTextChangedListener(new

                                                           TextWatcher() {
                                                               @Override
                                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                               }

                                                               @Override
                                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                   if (dataList != null) {
                                                                       dataList.get(getAdapterPosition()).setMemberEducation(memberEducation.getText().toString());
                                                                   }
                                                               }

                                                               @Override
                                                               public void afterTextChanged(Editable s) {

                                                               }
                                                           });

            memberEducationType.addTextChangedListener(new

                                                               TextWatcher() {
                                                                   @Override
                                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                   }

                                                                   @Override
                                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                       if (dataList != null) {
                                                                           dataList.get(getAdapterPosition()).setMemberEducationType(memberEducationType.getText().toString());
                                                                       }
                                                                   }

                                                                   @Override
                                                                   public void afterTextChanged(Editable s) {

                                                                   }
                                                               });

            memberJob.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             if (dataList != null) {
                                                                 dataList.get(getAdapterPosition()).setMemberJob(memberJob.getText().toString());
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {

                                                         }
                                                     });

            memberJobLoss.addTextChangedListener(new

                                                         TextWatcher() {
                                                             @Override
                                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                             }

                                                             @Override
                                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                 if (dataList != null) {
                                                                     dataList.get(getAdapterPosition()).setMemberJobLoss(memberJobLoss.getText().toString());
                                                                 }
                                                             }

                                                             @Override
                                                             public void afterTextChanged(Editable s) {

                                                             }
                                                         });

            memberJobType.addTextChangedListener(new

                                                         TextWatcher() {
                                                             @Override
                                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                             }

                                                             @Override
                                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                 if (dataList != null) {
                                                                     dataList.get(getAdapterPosition()).setMemberJobtype(memberJobType.getText().toString());
                                                                 }
                                                             }

                                                             @Override
                                                             public void afterTextChanged(Editable s) {

                                                             }
                                                         });

            memberMartialStatus.addTextChangedListener(new

                                                               TextWatcher() {
                                                                   @Override
                                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                   }

                                                                   @Override
                                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                       if (dataList != null) {
                                                                           dataList.get(getAdapterPosition()).setMemberMartialStatus(memberMartialStatus.getText().toString());
                                                                       }
                                                                   }

                                                                   @Override
                                                                   public void afterTextChanged(Editable s) {

                                                                   }
                                                               });
            memberNRI.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             if (dataList != null) {
                                                                 dataList.get(getAdapterPosition()).setIsNRI(memberNRI.getText().toString());
                                                                 if (memberNRI.getText().toString().equalsIgnoreCase(activity.getString(R.string.cmn_yes))){
                                                                     memberNRK.setText(activity.getString(R.string.cmn_no));
                                                                 }
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {

                                                         }
                                                     });
            memberNRK.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             if (dataList != null) {
                                                                 dataList.get(getAdapterPosition()).setIsNRK(memberNRK.getText().toString());
                                                                 if (memberNRK.getText().toString().equalsIgnoreCase(activity.getString(R.string.cmn_yes))){
                                                                     memberNRI.setText(activity.getString(R.string.cmn_no));
                                                                 }
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {

                                                         }
                                                     });

            memberGender.addTextChangedListener(new

                                                        TextWatcher() {
                                                            @Override
                                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                            }

                                                            @Override
                                                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                if (dataList != null) {
                                                                    dataList.get(getAdapterPosition()).setMemberGender(memberGender.getText().toString());
                                                                }
                                                            }

                                                            @Override
                                                            public void afterTextChanged(Editable s) {

                                                            }
                                                        });

            memberStudent.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                             if (dataList != null) {
                                                                 dataList.get(getAdapterPosition()).setIsStudent(memberStudent.getText().toString());
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {

                                                         }
                                                     });

            pensionAdd.setOnClickListener(v -> pensionAdapter.addNewItem());
            pensionRemove.setOnClickListener(v -> pensionAdapter.removeItem());
            view.findViewById(R.id.btnMemNameInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnMemAgeYearInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnMemAgeMonthInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnJobInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnJobLossInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnJobTypeInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnIsNRKInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnIsNRIInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnEducationInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnEduTypeInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnDisabilityInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnDisabilityPercentInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnStudentInfo).setOnClickListener(infoClick);
        }

    }


    MemberDetailsAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.isValidation = false;
    }

    void addNewItem() {
        if (dataList != null) {
            dataList.add(new MemberDetailsItem());
            isValidation = false;
            notifyDataSetChanged();
        }
    }

    void removeItem() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(dataList.size() - 1);
            setValidation(false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.memberTitle.setText(String.format(Locale.ENGLISH, "%s %d", activity.getString(R.string.member_title), position + 1));
        MemberDetailsItem data = getDataList().get(position);
        holder.memberName.setText(data.getMemberName());

        if (data.getMemberAge().contains(activity.getString(R.string.member_month))) {
            holder.memberAgeMonth.setText(data.getMemberAge().replace(activity.getString(R.string.member_month), "").trim());
        } else {
            holder.memberAge.setText(data.getMemberAge().replace(activity.getString(R.string.member_year), "").trim());
        }
        holder.memberGender.setText(data.getMemberGender());
        holder.memberNRI.setText(data.getIsNRI());
        holder.memberNRK.setText(data.getIsNRK());
        holder.memberMartialStatus.setText(data.getMemberMartialStatus());
        holder.memberEducation.setText(data.getMemberEducation());
        holder.memberEducationType.setText(data.getMemberEducationType());
        holder.memberJob.setText(data.getMemberJob());
        holder.memberJobLoss.setText(data.getMemberJobLoss());
        holder.memberJobType.setText(data.getMemberJobtype());

        for (CommonItem item : Objects.requireNonNull(IPMSApp.getAppInstance().getLocMainItem().getEducations())) {
            if (item.getContent().equalsIgnoreCase(data.getMemberEducation())) {
                holder.memberEducationType.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_member_view, item.getSubList()));
            }
        }

        ((MemberPensionAdapter) Objects
                .requireNonNull(holder.layoutMemberPension.getAdapter()))
                .setDataList(data.getMemberPension());

        holder.memberDisability.setText(data.getMemberDisability());
        holder.memberDisabilityPercent.setText(data.getMemberDisabilityPercent());
        holder.memberStudent.setText(data.getIsStudent());

        ((MemberDiseaseAdapter) Objects
                .requireNonNull(holder.layoutMemberDisease.getAdapter()))
                .setDataList(data.getMemberDisease());

        if (!isSurveyOpenEdit) {
            disableChildView( holder.layoutContainer);
//            if(diseaseAdapter!=null){
//                diseaseAdapter.updateSurveyOpenEditMode(false);
//                diseaseAdapter.notifyDataSetChanged();
//            }

//            if(pensionAdapter!=null)
//            {
//                pensionAdapter.updateSurveyOpenEditMode(false);
//                pensionAdapter.notifyDataSetChanged();
//            }
        }
        if (isValidation) {
            if (Objects.requireNonNull(holder.memberName.getText()).toString().length() == 0) {
                holder.layoutMemberName.setError(activity.getString(R.string.error_member_name));
            } else {
                holder.layoutMemberName.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberAge.getText()).toString().length() == 0
                    && Objects.requireNonNull(holder.memberAgeMonth.getText()).toString().length() == 0) {
                holder.layoutMemberAge.setError(activity.getString(R.string.error_member_age));
                holder.layoutMemberAgeMonth.setError(activity.getString(R.string.error_member_age));
            } else {
                holder.layoutMemberAge.setErrorEnabled(false);
                holder.layoutMemberAgeMonth.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberGender.getText()).toString().length() == 0) {
                holder.layoutMemberGender.setError(activity.getString(R.string.error_member_gender));
            } else {
                holder.layoutMemberGender.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberMartialStatus.getText()).toString().length() == 0) {
                holder.layoutMemberMartialStatus.setError(activity.getString(R.string.error_member_martial_status));
            } else {
                holder.layoutMemberMartialStatus.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberNRK.getText()).toString().length() == 0) {
                holder.layoutMemberNRK.setError(activity.getString(R.string.error_member_nrk));
            } else {
                holder.layoutMemberNRK.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberNRI.getText()).toString().length() == 0) {
                holder.layoutMemberNRI.setError(activity.getString(R.string.error_member_nri));
            } else {
                holder.layoutMemberNRI.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberEducation.getText()).toString().length() == 0) {
                holder.layoutMemberEducation.setError(activity.getString(R.string.error_member_education));
            } else {
                holder.layoutMemberEducation.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberEducationType.getText()).toString().length() == 0) {
                holder.layoutMemberEducationType.setError(activity.getString(R.string.error_member_education_type));
            } else {
                holder.layoutMemberEducationType.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberJobType.getText()).toString().length() == 0) {
                holder.layoutMemberJobType.setError(activity.getString(R.string.error_member_job_type));
            } else {
                holder.layoutMemberJobType.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberJob.getText()).toString().length() == 0) {
                holder.layoutMemberJob.setError(activity.getString(R.string.error_member_job));
            } else {
                holder.layoutMemberJob.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberJobLoss.getText()).toString().length() == 0) {
                holder.layoutMemberJobLoss.setError(activity.getString(R.string.error_member_job_loss));
            } else {
                holder.layoutMemberJobLoss.setErrorEnabled(false);
            }

            if (data.getMemberPension().contains("")) {
                ((MemberPensionAdapter) Objects
                        .requireNonNull(holder.layoutMemberPension.getAdapter())).setValidation(true);
            } else {
                ((MemberPensionAdapter) Objects
                        .requireNonNull(holder.layoutMemberPension.getAdapter())).setValidation(false);
            }

            if (data.getMemberDisease().contains("")) {
                ((MemberDiseaseAdapter) Objects
                        .requireNonNull(holder.layoutMemberDisease.getAdapter())).setValidation(true);
            } else {
                ((MemberDiseaseAdapter) Objects
                        .requireNonNull(holder.layoutMemberDisease.getAdapter())).setValidation(false);
            }

            if (Objects.requireNonNull(holder.memberDisability.getText()).toString().length() == 0) {
                holder.layoutMemberDisability.setError(activity.getString(R.string.error_member_disability));
            } else {
                holder.layoutMemberDisability.setErrorEnabled(false);
            }

            if (Objects.requireNonNull(holder.memberDisabilityPercent.getText()).toString().length() == 0) {
                holder.layoutMemberDisabilityPercent.setError(activity.getString(R.string.error_member_disability_percentage));
            } else {
                holder.layoutMemberDisabilityPercent.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(holder.memberStudent.getText()).toString().length() == 0) {
                holder.layoutMemberStudent.setError(activity.getString(R.string.error_member_student));
            } else {
                holder.layoutMemberStudent.setErrorEnabled(false);
            }

        } else {
            holder.layoutMemberName.setErrorEnabled(false);
            holder.layoutMemberAge.setErrorEnabled(false);
            holder.layoutMemberAgeMonth.setErrorEnabled(false);
            holder.layoutMemberGender.setErrorEnabled(false);
            holder.layoutMemberMartialStatus.setErrorEnabled(false);
            holder.layoutMemberNRK.setErrorEnabled(false);
            holder.layoutMemberNRI.setErrorEnabled(false);
            holder.layoutMemberJob.setErrorEnabled(false);
            holder.layoutMemberJobLoss.setErrorEnabled(false);
            holder.layoutMemberJobType.setErrorEnabled(false);
            holder.layoutMemberEducationType.setErrorEnabled(false);
            holder.layoutMemberEducation.setErrorEnabled(false);
            holder.layoutMemberDisability.setErrorEnabled(false);
            holder.layoutMemberDisabilityPercent.setErrorEnabled(false);
            holder.layoutMemberStudent.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<MemberDetailsItem> getDataList() {
        return dataList;
    }

    void setDataList(ArrayList<MemberDetailsItem> dataList) {
        this.dataList = dataList;
        setValidation(false);
    }

    void setValidation(boolean validation) {
        isValidation = validation;
        notifyDataSetChanged();
    }


    public View.OnClickListener infoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnMemNameInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_name),InfoVideoNames.MEMBER_NAME_INFO_VIDEO);
                    break;
                case R.id.btnMemAgeYearInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_age_year),InfoVideoNames.MEMBER_AGE_YEARS_INFO_VIDEO);
                    break;
                case R.id.btnMemAgeMonthInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_age_month),InfoVideoNames.MEMBER_AGE_MONTHS_INFO_VIDEO);
                    break;
                case R.id.btnJobInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_job_info),InfoVideoNames.MEMBER_JOB_INFO_VIDEO);
                    break;
                case R.id.btnJobLossInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_job_loss_info),InfoVideoNames.MEMBER_JOB_LOSS_CORONA_INFO_VIDEO);
                    break;
                case R.id.btnJobTypeInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_job_type_info),InfoVideoNames.MEMBER_JOB_TYPE_INFO_VIDEO);
                    break;
                case R.id.btnIsNRKInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_is_nrk),InfoVideoNames.MEMBER_NRK_INFO_VIDEO);
                    break;
                case R.id.btnIsNRIInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_is_nri),InfoVideoNames.MEMBER_NRI_INFO_VIDEO);
                    break;
                case R.id.btnEducationInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_education),InfoVideoNames.MEMBER_EDUCATION_INFO_VIDEO);
                    break;
                case R.id.btnEduTypeInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_edu_type),InfoVideoNames.MEMBER_EDUCATION_TYPE_INFO_VIDEO);
                    break;
                case R.id.btnDisabilityInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_disability),InfoVideoNames.MEMBER_DISABILITY_INFO_VIDEO);
                    break;
                case R.id.btnDisabilityPercentInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_disability_percent),InfoVideoNames.MEMBER_DISABILITY_PERCENTAGE_INFO_VIDEO);
                    break;
                case R.id.btnStudentInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_mem_student),InfoVideoNames.MEMBER_STUDENT_INFO_VIDEO);
                    break;
            }
        }
    };
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;

    }

}