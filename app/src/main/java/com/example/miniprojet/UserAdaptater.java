package com.example.miniprojet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.miniprojet.dataBase.Users;


public class UserAdaptater extends ArrayAdapter<Users>{

    public UserAdaptater(Context mContext, List<Users> usersList){
        super(mContext, R.layout.template_user, usersList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Récupération de la multiplication
        final Users users = getItem(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_user, parent, false);

        // Récupération des objets graphiques dans le template
        TextView prenom = (TextView) rowView.findViewById(R.id.Template_user_prenom);
        TextView nom = (TextView) rowView.findViewById(R.id.Template_user_nom);

        //
        prenom.setText(users.getPrenom());
        nom.setText(users.getNom());

        //
        return rowView;
    }
}
