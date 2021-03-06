package com.example.myweaponroomstorage

import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class RvAdapterWeapon(var weaponManager: WeaponManager): RecyclerView.Adapter<RvAdapterWeapon.WeaponViewHolder>() {

    var df = SimpleDateFormat("d-M-y")

    class WeaponViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextViewWeaponName: EditText = itemView.findViewById(R.id.editTextWeaponName)
        var editTextWeaponMaterial: EditText = itemView.findViewById(R.id.editTextWeaponMaterial)
        var editTextWeaponDate: EditText = itemView.findViewById(R.id.editTextWeaponDate)
        var buttonWeaponDelete: Button = itemView.findViewById(R.id.buttonWeaponDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.weapon_view, parent, false)

        return WeaponViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        var weapon = weaponManager.getWeapon(position)
        with(holder) {
            editTextViewWeaponName.setText(weapon.name)
            editTextWeaponMaterial.setText(weapon.material)
            editTextWeaponDate.setText(df.format(weapon.date))
            buttonWeaponDelete.setOnClickListener {
                weaponManager.deleteWeapon(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, weaponManager.count)
            }
        }

    }

    override fun getItemCount(): Int {
        return weaponManager.count
    }
}