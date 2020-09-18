package com.neillon.cashee.ui.tutorial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neillon.cashee.R
import com.neillon.cashee.databinding.ItemTutorialBinding
import com.neillon.cashee.ui.tutorial.Tutorial
import kotlinx.android.synthetic.main.item_tutorial.view.*

class TutorialAdapter : RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder>() {

    private lateinit var binding: ItemTutorialBinding
    private var items = mutableListOf<Tutorial>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        binding = ItemTutorialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TutorialViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        val tutorial = items[position]
        holder.itemView.imageViewTutorial.setImageResource(tutorial.image)
        holder.itemView.textViewTitle.text = tutorial.title
        holder.itemView.textViewDescription.text = tutorial.description
    }

    override fun getItemCount(): Int = 3

    inner class TutorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        items = mutableListOf(
            Tutorial(
                "Registre suas despesas",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n",
                R.drawable.ic_dashboard
            ),
            Tutorial(
                "Compartilhe seus resultados",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n",
                R.drawable.ic_person
            ),
            Tutorial(
                "Visualize seu balanço mensal",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n",
                R.drawable.ic_pie_chart
            )
        )
    }
}