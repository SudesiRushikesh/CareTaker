package com.hdfc.newzeal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hdfc.adapters.RatingCompletedAdapter;
import com.hdfc.model.ActivityFeedBackModel;
import com.hdfc.model.ActivityModel;
import com.hdfc.model.RatingCompletedModel;
import com.hdfc.newzeal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewRatingCompletedActivityFragment extends Fragment {

    ListView listView;
    TextView emptyTextView;
    ArrayList<RatingCompletedModel> ratingCompletedModels = new ArrayList<RatingCompletedModel>();
    private ActivityModel activityListModel;
    private RatingCompletedAdapter ratingCompletedAdapter;

    public ViewRatingCompletedActivityFragment() {
        // Required empty public constructor
    }

    public static ViewRatingCompletedActivityFragment newInstance(ActivityModel _activityModel) {
        ViewRatingCompletedActivityFragment fragment = new ViewRatingCompletedActivityFragment();
        Bundle args = new Bundle();
        args.putSerializable("ACTIVITY_COMPLETE", _activityModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_rating_completed_activity, container, false);
        listView = (ListView) view.findViewById(R.id.listViewRatings);

        emptyTextView = (TextView) view.findViewById(android.R.id.empty);

        activityListModel = (ActivityModel) this.getArguments().getSerializable("ACTIVITY_COMPLETE");

        ratingCompletedModels.clear();

        ratingCompletedAdapter = new RatingCompletedAdapter(getActivity(), ratingCompletedModels);
        listView.setAdapter(ratingCompletedAdapter);
        listView.setEmptyView(emptyTextView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (activityListModel != null) {

            for (int i = 0; i < activityListModel.getActivityFeedBackModels().size(); i++) {

                ActivityFeedBackModel activityFeedBackModel = activityListModel.getActivityFeedBackModels().get(i);

                RatingCompletedModel ratingCompletedModel = new RatingCompletedModel();
                ratingCompletedModel.setImg(R.drawable.hungal_circle);
                ratingCompletedModel.setPersonName(activityFeedBackModel.getStrFeedBackBy());
                ratingCompletedModel.setFeedback(activityFeedBackModel.getStrFeedBackMess());
                ratingCompletedModel.setTimeDate(activityFeedBackModel.getStrFeedBackTime());
                ratingCompletedModel.setRatingSmiley(R.drawable.smiley_icon_32);

                ratingCompletedModels.add(ratingCompletedModel);
            }
            ratingCompletedAdapter.notifyDataSetChanged();
        }
    }
}
