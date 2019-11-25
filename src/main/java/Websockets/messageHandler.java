package Websockets;

import org.json.JSONObject;

public class messageHandler {

    public messageHandler()
    {

    }

    public void handleMessage(JSONObject json, Game game)
    {
        try {
            if (json.getString("task").equals("bullet")) {
                bulletShot(json);

            }
                else {
                throw new IllegalArgumentException();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void bulletShot(JSONObject json)
    {
        int x = 0;
        int y = 0;
        int horizontal = 0;
        int vertical = 0;
        boolean player1turn = false;

        try {
            x = json.getInt("x");
            y = json.getInt("y");
            horizontal = json.getInt("horizontal");
            vertical = json.getInt("vertical");
            player1turn = json.getBoolean("player1turn");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(x);
        System.out.println(y);
        System.out.println(horizontal);
        System.out.println(vertical);
        System.out.println(player1turn);
    }
}
