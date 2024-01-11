package in.ults.gisurvey.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import in.ults.gisurvey.data.model.api.SurveyPoints;
import in.ults.gisurvey.data.model.items.GridItem;
import in.ults.gisurvey.ui.serverSurvey.list.ServerSurveyListAdapter;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import in.ults.gisurvey.data.model.api.Subordinates;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.main.completed.CompletedSurveyListAdapter;
import in.ults.gisurvey.ui.main.partialsurvey.PartialSurveyListAdapter;
import in.ults.gisurvey.ui.main.surveyor.SurveyorListAdapter;
import in.ults.gisurvey.ui.main.syncgrid.SyncGridAdapter;
import in.ults.gisurvey.ui.main.synclist.SyncListAdapter;
import in.ults.gisurvey.ui.survey.building.BuildingFloorAreaAdapter;
import in.ults.gisurvey.ui.survey.building.BuildingRoofTypeAdapter;
import in.ults.gisurvey.ui.survey.groundfloorselection.GroundFloorSelectionAdapter;
import in.ults.gisurvey.ui.survey.more.VehicleAdapter;
import in.ults.gisurvey.ui.survey.owner.OwnerListAdapter;
import in.ults.gisurvey.ui.survey.road.RoadListAdapter;
import in.ults.gisurvey.ui.survey.selection.ScreenSelectionGridAdapter;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridAdapter;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewSingleSelectAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;
import in.ults.gisurvey.utils.adapters.StateListAdapter;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }


    @BindingAdapter({"adapter"})
    public static void setFlexRecycleViewAdapter(RecyclerView recyclerView, List<CommonItem> dataItem) {
        if (recyclerView.getAdapter() instanceof FlexRecyclerViewSingleSelectAdapter) {
            FlexRecyclerViewSingleSelectAdapter adapter = (FlexRecyclerViewSingleSelectAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (dataItem != null) {
                    adapter.addItems(dataItem);
                }
            }
        } else if (recyclerView.getAdapter() instanceof FlexRecyclerViewMultiSelectAdapter) {
            FlexRecyclerViewMultiSelectAdapter adapter = (FlexRecyclerViewMultiSelectAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (dataItem != null) {
                    adapter.addItems(dataItem);
                }
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void setCompletedPartialRecycleViewAdapter(RecyclerView recyclerView, List<Survey> dataItem) {
        if (recyclerView.getAdapter() instanceof PartialSurveyListAdapter) {
            PartialSurveyListAdapter adapter = (PartialSurveyListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (dataItem != null) {
                    adapter.addItems(dataItem);
                }
            }
        }
    }
    @BindingAdapter({"adapter"})
    public static void setServerSurveyRecyclerViewAdapter(RecyclerView recyclerView, List<SurveyPoints> dataItem) {
        if (recyclerView.getAdapter() instanceof ServerSurveyListAdapter) {
            ServerSurveyListAdapter adapter = (ServerSurveyListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (dataItem != null) {
                    adapter.addItems(dataItem);
                }
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void setSurveyorRecyclerViewAdapter(RecyclerView recyclerView, List<Subordinates> dataItem) {
        if (recyclerView.getAdapter() instanceof SurveyorListAdapter) {
            SurveyorListAdapter adapter = (SurveyorListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (dataItem != null) {
                    adapter.addItems(dataItem);
                }
            }
        }
    }


    @BindingAdapter({"adapter"})
    public static void setFloorAreaRecycleViewAdapter(RecyclerView recyclerView, ArrayList<BuildingDetailsFloorAreaItem> dataItem) {
        if (recyclerView.getAdapter() instanceof BuildingFloorAreaAdapter) {
            BuildingFloorAreaAdapter adapter = (BuildingFloorAreaAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                if (dataItem != null) {
                    adapter.setDataList(dataItem);
                }
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void setRoofTypeRecycleViewAdapter(RecyclerView recyclerView, ArrayList<BuildingDetailsRoofItem> dataItem) {
        if (recyclerView.getAdapter() instanceof BuildingRoofTypeAdapter) {
            BuildingRoofTypeAdapter adapter = (BuildingRoofTypeAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                if (dataItem != null) {
                    adapter.setDataList(dataItem);
                }
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void setVehicleRecycleViewAdapter(RecyclerView recyclerView, ArrayList<VehicleDetailsItem> dataItem) {
        if (recyclerView.getAdapter() instanceof VehicleAdapter) {
            VehicleAdapter adapter = (VehicleAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                if (dataItem != null) {
                    adapter.setDataList(dataItem);
                }
            }
        }
    }


    @BindingAdapter({"adapter", "selectedPosition"})
    public static void setGroundFloorAdapter(RecyclerView recyclerView, int floorCount, int selectedPosition) {
        if (recyclerView.getAdapter() instanceof GroundFloorSelectionAdapter) {
            GroundFloorSelectionAdapter adapter = (GroundFloorSelectionAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.updateFloorCount(selectedPosition, floorCount);
            }
        }
    }

    @BindingAdapter({"imageUrl", "defaultImage"})
    public static void setImageUrl(ImageView imageView, String url, Drawable defaultImage) {
        if (url != null && url.length() > 0) {
            Context context = imageView.getContext();

            RequestOptions optionsGrid = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .dontAnimate();

            Glide.with(context)
                    .load(url)
                    .apply(optionsGrid)
                    .into(imageView);
        } else {
            imageView.setImageDrawable(defaultImage);
        }
    }

    @BindingAdapter({"surveyImage", "defaultImage"})
    public static void setSurveyImage(ImageView imageView, String url, Drawable defaultImage) {
        if (url != null && url.length() > 0) {
            Context context = imageView.getContext();

            RequestOptions optionsGrid = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .dontAnimate();

            Glide.with(context)
                    .load(url)
                    .apply(optionsGrid)
                    .into(imageView);
        } else {
            imageView.setImageDrawable(defaultImage);
        }
    }


    /*@BindingAdapter({"imageUrl", "defaultImage"})
    public static void setImageUrl(ImageView imageView, String url,@DrawableRes int defaultImage) {
        if (url != null && url.length() > 0) {
            Context context = imageView.getContext();

            RequestOptions optionsGrid = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .dontAnimate();

            Glide.with(context)
                    .load(url)
                    .apply(optionsGrid)
                    .into(imageView);
        } else {
            imageView.setImageResource(defaultImage);
        }
    }*/

    @BindingAdapter({"adapter"})
    public static void setIPMSSpinnerAdapter(IPMSSpinner ipmsSpinner, List<CommonItem> commonItems) {
        if (ipmsSpinner.getAdapter() instanceof CommonDropDownAdapter) {
            CommonDropDownAdapter adapter = (CommonDropDownAdapter) ipmsSpinner.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }

    }

     @BindingAdapter({"adapter"})
    public static void setSyncListAdapter(RecyclerView recyclerView, List<SurveyWithProperty> commonItems) {
        if (recyclerView.getAdapter() instanceof SyncListAdapter) {
            SyncListAdapter adapter = (SyncListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }else if (recyclerView.getAdapter() instanceof CompletedSurveyListAdapter) {
            CompletedSurveyListAdapter adapter = (CompletedSurveyListAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                 adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }

    }

    @BindingAdapter({"adapter"})
    public static void setOwnerListAdapter(AppCompatAutoCompleteTextView textView, List<Owner> commonItems) {
        if (textView.getAdapter() instanceof OwnerListAdapter) {
            OwnerListAdapter adapter = (OwnerListAdapter) textView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }

    }

    @BindingAdapter({"adapter"})
    public static void setScreenSelectionGridAdapter(RecyclerView recyclerView, List<GridItem> commonItems) {
        if (recyclerView.getAdapter() instanceof ScreenSelectionGridAdapter) {
            ScreenSelectionGridAdapter adapter = (ScreenSelectionGridAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }
    }

    @BindingAdapter({"adapter"})
    public static void setRoadListAdapter(AppCompatAutoCompleteTextView textView, List<Road> commonItems) {
        if (textView.getAdapter() instanceof RoadListAdapter) {
            RoadListAdapter adapter = (RoadListAdapter) textView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }

    }



    @BindingAdapter({"adapter"})
    public static void setPostOfficeListAdapter(AppCompatAutoCompleteTextView textView, List<CommonItem> commonItems) {
        if (textView.getAdapter() instanceof PostOfficeListAdapter) {
            PostOfficeListAdapter adapter = (PostOfficeListAdapter) textView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    adapter.addItems(commonItems);
                }
            }
        }
    }


    @BindingAdapter({"adapter"})
    public static void setStateListAdapter(AppCompatAutoCompleteTextView textView, Set<String> commonItems) {
        if (textView.getAdapter() instanceof StateListAdapter) {
            StateListAdapter adapter = (StateListAdapter) textView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                if (commonItems != null) {
                    List<String> data = new ArrayList<>(commonItems);
                    adapter.addItems(data);
                }
            }
        }
    }


    @BindingAdapter({"adapter"})
    public static void setGridAdapter(RecyclerView recyclerView, int count) {
        if (recyclerView.getAdapter() instanceof SurveyGridAdapter) {
            SurveyGridAdapter gridAdapter = (SurveyGridAdapter) recyclerView.getAdapter();
            if (gridAdapter != null) {
                gridAdapter.clearItems();
                gridAdapter.addItems(count);
            }
        }else if (recyclerView.getAdapter() instanceof SyncGridAdapter) {
            SyncGridAdapter gridAdapter = (SyncGridAdapter) recyclerView.getAdapter();
            if (gridAdapter != null) {
                gridAdapter.clearItems();
                gridAdapter.setTotalSurveyCount(count);
            }
        }
    }

    @BindingAdapter("imageDrawable")
    public static void loadImageDrawable(ImageView view, @DrawableRes int imageId) {
        if (imageId != -1) {
            view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), imageId));
        }
    }
}
