package com.sad490.smartscrape.Posters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sad490.smartscrape.NetWork.GrabImage;
import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Posters.dummy.DummyContent;
import com.sad490.smartscrape.Posters.dummy.DummyContent.DummyItem;
import com.sad490.smartscrape.Recommand.RecItem;
import com.sad490.smartscrape.Recommand.RecItemViewBinder;
import com.sad490.smartscrape.Recommand.RecommandFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnPostersListener}
 * interface.
 */
public class PostersFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnPostersListener mListener;

    private static List<Posters> posters = new ArrayList<>();

    private static List<String> names = new ArrayList<>();
    private static List<Bitmap> bitmaps = new ArrayList<>();

    private static String url_to_load;
    public static MultiTypeAdapter adapter = new MultiTypeAdapter();

    private static final int Load_Image_Finish = 1;

    public static int image_num = 0;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostersFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PostersFragment newInstance(int columnCount) {
        PostersFragment fragment = new PostersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch( msg.what ) {
                case Load_Image_Finish:
                    Log.d("Load Data Finished", "Finished");
                    adapter.setItems(posters);
                    adapter.notifyDataSetChanged();
                    break;
                // case Load_Detail_Data_finished:
            }
        }
    };

    Runnable LoadingImages = new Runnable() {
        @Override
        public void run() {
            try {
                List<Poster_element> urls = new ArrayList<>();

                urls = GrabImage.grubPostersHeaders(User.getHttpclient(), "http://111.230.181.121/pub_list");

                posters.clear();
                for (Poster_element url : urls) {
                    Log.d("Pub Urls : ", url.getTitle());
                    Log.d("Images Urls : ", url.getImage_url());
                    Log.d("Id Urls : ", url.getPub_url());
                    // names.add(url.getTitle());
                    url_to_load = "http://111.230.181.121/" + url.getImage_url();
                    posters.add(new Posters(url.getTitle(), url_to_load, url.getPub_url()));
                }
                Message message = mHandler.obtainMessage();
                message.what = Load_Image_Finish;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                try {
                    Thread.sleep(5000);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
                new Thread(LoadingImages).start();
                e.printStackTrace();
            }
        }
    };

//    Runnable ImageLoader = new Runnable() {
//        @Override
//        public void run() {
//            synchronized (this) {
//                bitmaps.add(GrabImage.getImage(User.getHttpclient(), url_to_load));
//            }
//            Log.d("Load Image", "Success .");
//            Log.d("Image Size : ", "" + bitmaps.get(image_num).getWidth());
//            ++image_num;
//            if (image_num == names.size()) {
//                Message message = mHandler.obtainMessage();
//                message.what = Load_Image_Finish;
//                mHandler.sendMessage(message);
//            }
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        adapter.setItems(posters);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posters_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // recyclerView.setAdapter(new PostersAdapter(DummyContent.ITEMS, mListener));
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(LoadingImages).start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostersListener) {
            mListener = (OnPostersListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        adapter = new MultiTypeAdapter();
        adapter.register(Posters.class, new PostersViewBinder(mListener, getContext()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static void setPosters(List<Posters> _posters) {
        posters = _posters;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPostersListener {
        // TODO: Update argument type and name
        void onPosterClick(Posters item);
    }
}
