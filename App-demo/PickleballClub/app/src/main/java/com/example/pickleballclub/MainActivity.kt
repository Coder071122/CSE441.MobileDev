package com.example.pickleballclub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pickleballclub.ui.theme.PickleballClubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickleballClubTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(demoPlayers, demoNews)
                }
            }
        }
    }
}

// ========== DATA MODEL ==========
data class Player(
    val member_code: String,
    val username: String,
    val avatar: String,
    val birthday: String,
    val hometown: String,
    val residence: String,
    val rating_single: Double,
    val rating_double: Double
)

data class News(
    val news_code: String,
    val title: String,
    val content: String,
    val image: String,
    val author: String,
    val timestamp: String
)

// ========== DEMO DATA ==========
val demoPlayers = listOf(
    Player("MBR001", "Nguyen Van A", "mbr001.jpg", "2005-10-10", "Ha Noi", "TP.HCM", 1.25, 1.1),
    Player("MBR002", "Tran Thi B", "mbr002.jpg", "2006-03-15", "Da Nang", "TP.HCM", 0.95, 1.05)
)

val demoNews = listOf(
    News("NEWS001", "Giao lưu đầu mùa", "Ngày 20/5 CLB tổ chức giao lưu...", "news001.jpg", "admin_uid_123", "2024-05-20T12:00:00")
)

// ========== UI COMPOSABLE ==========
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(players: List<Player>, newsList: List<News>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("CLB Pickleball") }) }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            item { SectionTitle("Danh sách hội viên") }
            items(players.size) {
                MemberCard(players[it])
            }

            item { SectionTitle("Tin tức hoạt động") }
            items(newsList.size) {
                NewsCard(newsList[it])
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun MemberCard(player: Player) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(player.username, fontWeight = FontWeight.Bold)
            Text("Mã: ${player.member_code}")
            Text("Sinh: ${player.birthday}")
            Text("Quê: ${player.hometown}")
            Text("Nơi ở: ${player.residence}")
            Text("Điểm đơn: ${player.rating_single} | đôi: ${player.rating_double}")
        }
    }
}

@Composable
fun NewsCard(news: News) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(news.title, fontWeight = FontWeight.Bold)
            Text(news.content, maxLines = 3)
            Text("Tác giả: ${news.author} | ${news.timestamp}", fontSize = 12.sp)
        }
    }
}
