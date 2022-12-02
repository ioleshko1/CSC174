package com.controller.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.springframework.lang.NonNull;


public class PlayerData {

    private static final List<Player> players = new ArrayList<>();

    public PlayerData() {

    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void addPlayer(Player toAdd)
    {
        players.add(toAdd);

        // Prepared statement
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://b8e9acc0d62333:d29d99ae@us-cdbr-east-06.cleardb.net/heroku_34f03c957b2bd86?reconnect=true")) {

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO PLAYER VALUES (?,?)");
            conn.setAutoCommit(false);

            preparedStatement.setString(1, toAdd.getPlayerName());
            preparedStatement.setString(2, toAdd.getLegendName());

            preparedStatement.executeUpdate();
            conn.commit();

        } catch (SQLException e) {

            System.out.println("toString(): " + e.toString());
            System.out.println("getMessage(): " + e.getMessage());

        }





    }

}
