package com.atifnaseem.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OnlineGameActivity extends AppCompatActivity {


    String playerSession = "";
    String userName = "";
    String otherPlayer = "";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_game);

        userName = getIntent().getExtras().get("user_name").toString();
        otherPlayer = getIntent().getExtras().get("other_player").toString();
        playerSession = getIntent().getExtras().get("player_session").toString();

    }



    public void GameBoardClick(View view){
        ImageView selectedImage = (ImageView) view;

        if(playerSession.length() <= 0){
            Intent intent = new Intent(getApplicationContext(), OnlineLoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            int selectedBlock = 0;
            switch ((selectedImage.getId())) {
                case R.id.iv_11: selectedBlock = 1; break;
                case R.id.iv_12: selectedBlock = 2; break;
                case R.id.iv_13: selectedBlock = 3; break;

                case R.id.iv_21: selectedBlock = 4; break;
                case R.id.iv_22: selectedBlock = 5; break;
                case R.id.iv_23: selectedBlock = 6; break;

                case R.id.iv_31: selectedBlock = 7; break;
                case R.id.iv_32: selectedBlock = 8; break;
                case R.id.iv_33: selectedBlock = 9; break;
            }

            myRef.child("playing").child(playerSession).child("block:"+selectedBlock).setValue(userName);
        }

        //PlayGame(selectedBlock, selectedImage);
    }
}
