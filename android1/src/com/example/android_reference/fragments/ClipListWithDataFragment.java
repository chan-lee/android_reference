package com.example.android_reference.fragments;

import java.util.ArrayList;

import com.example.android_reference.R;
import com.example.android_reference.loaders.DataLoader;
import com.example.android_reference.loaders.ClipDataLoader;
import com.example.android_reference.models.Clip;

//import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ClipListWithDataFragment extends BaseFragment {
    private ClipArrayAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_clip_list, container,
				false);

		// make clip list
		ClipDataLoader clipDataLoader = getDataManager().getClipDataLoader();
		ArrayList<Clip> savedClips = clipDataLoader.getData(false,
    		new DataLoader.Listener<ArrayList<Clip>>() {
                @Override
                public void onDataLoaded(ArrayList<Clip> updatedClips) {
                    mAdapter.clear();
                    mAdapter.addAll(updatedClips);
                    mAdapter.notifyDataSetChanged();
                }
            });
		mAdapter = new ClipArrayAdapter(getActivity(), R.layout.row_clip, savedClips);
		ListView listView = (ListView) rootView.findViewById(R.id.clip_listview);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
		    @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		        Log.i("item list", "clicked");
            }
        });

		return rootView;
	}

    static final RadioGroup.OnCheckedChangeListener mToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    public class ClipArrayAdapter extends ArrayAdapter<Clip> {
    	private LayoutInflater _inflater = null;
    	private final int _resourceId;

    	class ViewHolder {
    		protected ImageView imgThumbnail;
    		protected TextView txtTitle;
    		protected ImageButton btnPlay;
    	}

    	public ClipArrayAdapter(Context context, int textViewResourceId,
			  					ArrayList<Clip> list) {
    		super(context, textViewResourceId, list);
    		this._inflater = LayoutInflater.from(context);
    		this._resourceId = textViewResourceId;
    	}

    	@Override
		public Clip getItem(int position) {
    		return super.getItem(position);
		}

    	@Override
    	public long getItemId(int position) {
    		return super.getItemId(position);
    	}

    	@Override
    	public View getView(final int position, View convertView, ViewGroup parent) {
    		View v = convertView;
    		ViewHolder viewHolder = null;
    		if (v == null) {
    			viewHolder = new ViewHolder();
    			v = _inflater.inflate(_resourceId, null);
    			viewHolder.imgThumbnail = (ImageView)v.findViewById(R.id.clip_thumbnail);
    			viewHolder.txtTitle = (TextView)v.findViewById(R.id.clip_title);
    			viewHolder.btnPlay = (ImageButton)v.findViewById(R.id.clip_play);
    			viewHolder.btnPlay.setOnClickListener(new OnClickListener() {
    			   @Override
    			   public void onClick(View v) {
    			       onPlayClicked(this, this, position, null);
    			   }
    			});
    			v.setTag(viewHolder);
    		}
    		else {
    			viewHolder = (ViewHolder)v.getTag();
    		}

    		Clip clip = getItem(position);
    		viewHolder.txtTitle.setText(clip.title);
    		getImageClient().display("http://" + clip.thumbnail, viewHolder.imgThumbnail);
    		return v;
    	}

    	protected void onPlayClicked(OnClickListener onClickListener,
    	        OnClickListener onClickListener2, int position, Object object) {

    	    Clip clip = getItem(position);

    	    Bundle bundle = new Bundle();
    	    bundle.putParcelable("clip", clip);
    	    Log.i("item list", "button clicked");
    	    /*
    	    ClipDetailFragment fragment = new ClipDetailFragment();
    	    fragment.setArguments(bundle);

    	    getActivity().getFragmentManager()
        	    .beginTransaction()
        	    //.setCustomAnimations(R.animator.anim_back_right, exit)
        	    //.replace(R.id.fragment_container, fragment)
        	    .replace(R.id.root_frame, fragment)
        	    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        	    .addToBackStack(null)
        	    .commit();

             */
    	}
    }

}
