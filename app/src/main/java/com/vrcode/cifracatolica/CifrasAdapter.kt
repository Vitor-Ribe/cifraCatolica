package com.vrcode.cifracatolica

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrcode.cifracatolica.model.Cifra
import com.vrcode.cifracatolica.utils.ImageResources


class CifrasAdapter(
    private var cifras: List<Cifra>,
    private val onClick: (Cifra) -> Unit,
    private val onLongClick: (Cifra) -> Unit // Adicionando a função para o click longo
) : RecyclerView.Adapter<CifrasAdapter.CifraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CifraViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cifra, parent, false)
        return CifraViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CifraViewHolder, position: Int) {
        val cifra = cifras[position]
        holder.bind(cifra)

        // Clique curto: Abre a cifra
        holder.itemView.setOnClickListener {
            onClick(cifra)
        }

        // Clique longo: Exibe a confirmação de exclusão
        holder.itemView.setOnLongClickListener {
            onLongClick(cifra)
            true // Retorna true para indicar que o evento foi consumido
        }
    }

    override fun getItemCount() = cifras.size

    class CifraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cifra: Cifra) {
            // Configurar o item (por exemplo, texto)
            itemView.findViewById<TextView>(R.id.textViewCifra).text = cifra.titulo
            itemView.findViewById<TextView>(R.id.tv_cantor).text = cifra.cantor

            // Configurar a imagem
            val imageView = itemView.findViewById<ImageView>(R.id.iv_cifra)
            val imageId = ImageResources.imageMap[cifra.image] ?: R.drawable.default_image
            imageView.setImageResource(imageId)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCifras: List<Cifra>) {
        cifras = newCifras
        notifyDataSetChanged()
    }
}

