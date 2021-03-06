package rdm.qhacks.com.musicmatch.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.stream.IntStream;

import rdm.qhacks.com.musicmatch.Activities.Settings;
import rdm.qhacks.com.musicmatch.Model.Adapters.InputPlaylistAdapter;
import rdm.qhacks.com.musicmatch.Model.Adapters.ReturnPlaylistAdapater;
import rdm.qhacks.com.musicmatch.R;

public class MusicMatchView extends ParentView {

    public MusicMatchView(ViewGroup viewGroup, Context context){
        this.viewGroup = viewGroup;
        this.context = context;

        //Set up HashMap to hold all views pertaining to this activity
        final int childCount = this.viewGroup.getChildCount();
        IntStream.range(0, childCount).forEachOrdered(n -> this.activityViews.put(this.context.getResources().getResourceEntryName(this.viewGroup.getChildAt(n).getId()), this.viewGroup.getChildAt(n)));
    }

    /**
     * @Method setupLayout : Sets up the basic static UI that the MusicMatch activity will load
     *                       Initialize and create the fragments
     */
    @Override
    public void setupLayout() {
        //Set backgound image
        this.viewGroup.setBackground(this.viewGroup.getResources().getDrawable(R.drawable.background, this.context.getTheme()));

        //Set title attributes
        Typeface font = Typeface.createFromAsset(this.context.getAssets(), "fonts/" + "OpenSans-Bold" + ".ttf");
        TextView title = (TextView) this.activityViews.get("MusicMatchTitle");
        title.setText(this.context.getString(R.string.app_name));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, 123);
        title.setTypeface(font);


        this.activityViews.get("PlayListName").setAlpha(0.70f);
        this.activityViews.get("Settings").setAlpha(0.70f);
        this.activityViews.get("FileFetchButton").setAlpha(0.70f);
        this.activityViews.get("FetchMusicButton").setAlpha(0.70f);

        // Set up settings button
        this.activityViews.get("Settings").setOnClickListener(view -> this.context.startActivity(new Intent(context, Settings.class)));

        //Setup Recycler View
        RecyclerView recyclerView = (RecyclerView) this.activityViews.get("Playlist");
        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

    }

    public void fillRecyclerViewInputList(){
        InputPlaylistAdapter inputPlaylistAdapter = new InputPlaylistAdapter();
        ((RecyclerView)this.activityViews.get("Playlist")).setAdapter(inputPlaylistAdapter);
    }

    public void fillRecyclverViewOutputList(){
        ReturnPlaylistAdapater returnPlaylistAdapater = new ReturnPlaylistAdapater();
        ((RecyclerView)this.activityViews.get("Playlist")).setAdapter(returnPlaylistAdapater);
    }
}
