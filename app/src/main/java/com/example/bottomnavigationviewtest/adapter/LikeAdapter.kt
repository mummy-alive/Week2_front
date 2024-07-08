import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.models.UserLikeResponse

class LikeAdapter(
    private val likes: List<UserLikeResponse>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    class LikeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.name_text)
        val trashIcon: ImageView = view.findViewById(R.id.imageTrash)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_like, parent, false)
        return LikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val profile = likes[position].to_id
        holder.nameText.text = profile.name

        holder.trashIcon.setOnClickListener {
            onDeleteClick(likes[position].id)
        }
    }

    override fun getItemCount() = likes.size
}
