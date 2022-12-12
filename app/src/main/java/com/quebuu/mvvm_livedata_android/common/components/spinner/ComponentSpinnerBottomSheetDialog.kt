package com.quebuu.mvvm_livedata_android.common.components.spinner

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import mvvm_livedata_android.R

class ComponentSpinnerBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var items: ArrayList<String>
    private var positionSelected: Int = -1
    private lateinit var itemClick: (Int, String) -> Unit
    private lateinit var eventDismiss: () -> Unit
    private lateinit var adapter: ComponentSpinnerBottomSheetAdapter
    private lateinit var rvItems: RecyclerView
    private lateinit var tvEmpty: TextView

    companion object {
        public const val TAG = "ComponentSpinnerBottomSheetDialog"
        private const val ARG_ITEMS = "ARG_ITEMS"
        private const val ARG_POSITION = "ARG_POSITION"
        fun newInstance(items: ArrayList<String>, positionSelected: Int = -1, itemClick: (Int, String) -> Unit, eventDismiss: () -> Unit): ComponentSpinnerBottomSheetDialog {
            val fragment = ComponentSpinnerBottomSheetDialog()
            val args = Bundle()
            args.putSerializable(ARG_ITEMS, items)
            args.putSerializable(ARG_POSITION, positionSelected)
            fragment.arguments = args
            fragment.itemClick = itemClick
            fragment.eventDismiss = eventDismiss
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.component_spinner_bottom_sheet, container, false)
        rvItems = view.findViewById(R.id.rvItems)
        tvEmpty = view.findViewById(R.id.tvEmpty)
        return view
    }

    override fun onViewCreated(view: View, args: Bundle?) {
        super.onViewCreated(view, args)

        arguments?.let {
            items = arguments?.getStringArrayList(ARG_ITEMS) as ArrayList<String>
            positionSelected = arguments?.getInt(ARG_POSITION, -1)!!
        }

        tvEmpty.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        rvItems.visibility = if (items.isEmpty()) View.GONE else View.VISIBLE

        adapter = ComponentSpinnerBottomSheetAdapter(items, positionSelected) { i: Int, s: String ->
            itemClick.invoke(i, s)
            eventDismiss.invoke()
            dismiss()
        }
        rvItems.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvItems.adapter = adapter
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        eventDismiss.invoke()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

}

class ComponentSpinnerBottomSheetAdapter(
    private val items: ArrayList<String>,
    private var positionSelected: Int,
    private val itemClick: (Int, String) -> Unit
) : RecyclerView.Adapter<ComponentSpinnerBottomSheetAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_spinner_item_bottom_sheet, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.ivCheck.visibility = if (position == positionSelected) View.VISIBLE else View.INVISIBLE
        holder.tvTitle.text = item
        holder.tvTitle.setTextColor(ContextCompat.getColor(holder.itemView.context, if (position == positionSelected) R.color.green_wiener else R.color.gray1))
        holder.itemView.setOnClickListener {
            itemClick.invoke(position, item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(
        itemView: View,
        val ivCheck: ImageView = itemView.findViewById(R.id.ivCheck),
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    ) : RecyclerView.ViewHolder(itemView)

    fun setPositionSelected(positionSelected: Int) {
        this.positionSelected = positionSelected
        notifyDataSetChanged()
    }

}