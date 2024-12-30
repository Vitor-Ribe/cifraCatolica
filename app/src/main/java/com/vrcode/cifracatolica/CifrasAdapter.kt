import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrcode.cifracatolica.R
import com.vrcode.cifracatolica.model.Cifra

class CifrasAdapter(
    private var cifras: List<Cifra>,
    private val onItemLongClick: (Cifra) -> Unit // Callback para clique longo
) : RecyclerView.Adapter<CifrasAdapter.CifraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CifraViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cifra, parent, false)
        return CifraViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CifraViewHolder, position: Int) {
        val cifra = cifras[position]
        holder.bind(cifra)

        // Detectar clique longo
        holder.itemView.setOnLongClickListener {
            onItemLongClick(cifra)
            true
        }
    }

    override fun getItemCount() = cifras.size

    class CifraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cifra: Cifra) {
            // Configurar o item (por exemplo, texto)
            itemView.findViewById<TextView>(R.id.textViewCifra).text = cifra.titulo
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCifras: List<Cifra>) {
        cifras = newCifras
        notifyDataSetChanged()
    }
}

