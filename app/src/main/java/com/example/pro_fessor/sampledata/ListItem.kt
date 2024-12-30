import com.example.pro_fessor.sampledata.MemberDto

sealed class ListItem {
    data class Header(val title: String) : ListItem()
    data class Contact(val member: MemberDto, val qualification: String) : ListItem()
}