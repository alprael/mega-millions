package edu.cnm.deepdive.megamillions.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import edu.cnm.deepdive.Generator;
import edu.cnm.deepdive.MMGenerator;
import edu.cnm.deepdive.megamillions.R;
import edu.cnm.deepdive.megamillions.view.PickAdapter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private Generator generator;
  private PickAdapter adapter;
  private RecyclerView pickListView;
  private List<int[]> picks;
  private Random rng;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    generator = new MMGenerator(new SecureRandom());
    pickListView = findViewById(R.id.pick_list_view);
    picks = new ArrayList<>();
    adapter = new PickAdapter(this, picks);
    pickListView.setAdapter(adapter);
    rng = new SecureRandom();
    FloatingActionButton fab = findViewById(R.id.add_pick);
    fab.setOnClickListener((view) -> {
        int[] pick = generator.generate();
        picks.add(pick);
        adapter.notifyItemInserted(picks.size() - 1);
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.action_clear:
        picks.clear();
        adapter.notifyDataSetChanged();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }
}
