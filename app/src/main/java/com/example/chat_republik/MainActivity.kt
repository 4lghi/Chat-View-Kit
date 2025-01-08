package com.example.chat_republik

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chat_republik.ui.theme.ChatrepublikTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.max

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Chat()
        }
    }
}



@Composable
fun Chat() {
    var inputMessage by remember { mutableStateOf("") }
    var messageReceived = "Budi 24-jan-2025 10:00 halo aku B"
    val messages = remember { mutableStateListOf<Triple<Boolean, String, String>>(
        Triple(true, "Halo aku A", "10:00 AM"),
        Triple(false, "Halo aku B", "10:20 AM"),
        Triple(true, "Halo aku A lagi", "12:00 AM"),
        Triple(false, "Ini pesan dari B lagi", "12:05 AM"),
        Triple(true, "Apa kabar A?", "12:10 AM"),
        Triple(false, "Baik, terima kasih B!", "12:15 AM"),
        Triple(true, "Ada rencana akhir pekan?", "12:30 AM"),
        Triple(false, "Mungkin nonton film, B.", "12:35 AM"),
        Triple(true, "Film apa yang ingin ditonton?", "12:40 AM"),
        Triple(false, "Mungkin yang baru, yang ada di bioskop.", "12:45 AM"),
        Triple(true, "Setuju! Aku sudah lama tidak nonton.", "12:50 AM"),
        Triple(false, "Kita atur waktu nanti.", "12:55 AM"),
        Triple(true, "Oke, nanti aku kabarin.", "01:00 AM"),
        Triple(false, "Baik, aku tunggu kabar dari A.", "01:05 AM"),
        Triple(true, "Sampai jumpa nanti ya!", "01:10 AM"),
        Triple(false, "Sampai jumpa A!", "01:15 AM")
        )
    } // Boolean for sender/receiver, String for message, String for time
//    Contoh struktur data:
//    [
//        Triple(true, "Halo aku A", "10:00 AM"),
//        Triple(false, "Halo aku B", "10:20 AM"),
//        Triple(true, "Halo aku A lagi", "12:00 AM"),
//    ]
    val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm"))
    var isMessageReceived by remember { mutableStateOf(false) }


    if(isMessageReceived){
        messages.add(Triple(false, "halo", currentTime))
        isMessageReceived = false
    }
//    SEBAGAI CONTOH, DATABASE MENGGUNAKAN FIRESTORE

    //    Load chat history dari server atau database
    LaunchedEffect(Unit) {

    }

//        jika ada pesan masuk
    if (isMessageReceived) {
        messages.add(Triple(false, "Halo aku B", currentTime))
        isMessageReceived = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(10.dp)
    ) {
//        Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.07f)
                .padding(horizontal = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "User",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Box(Modifier.fillMaxWidth()){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable {  }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){

            //        ISI CHAT
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ){
                items(messages) { (isSender, text, time) ->
                    if (isSender) {
                        SenderMessage(text = text, time = time)
                    } else {
                        ReceiverMessage(text = text, time = time)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

//           CHAT INPUT FIELD
            ChatInputField(
                inputMessage = inputMessage,
                onMessageChange = { inputMessage = it },
                onSendMessage = {
//                    pengecekan input tidak kosong ataupun ada hal lain yang perlu dicek sebelum mengirim pesan
                    if (inputMessage.isNotEmpty()){
                        messages.add(Triple(true, inputMessage, currentTime))
                    }
                    // Tangani pengiriman pesan di sini
                    inputMessage = "" // Bersihkan input setelah pengiriman
                }
            )
        }
    }
}

@Composable
fun SenderMessage(
    text: String = "",
    time: String = ""
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Lebar layar dalam DP
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = time,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 0.dp))
                .background(MaterialTheme.colorScheme.primary)
                .widthIn(max = screenWidth*0.65f)
                .padding(horizontal = 15.dp, vertical = 15.dp)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun ReceiverMessage(
    text: String = "",
    time: String = ""
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Lebar layar dalam DP
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 0.dp, bottomEnd = 20.dp))
                .background(MaterialTheme.colorScheme.primary)
                .widthIn(max = screenWidth*0.65f)
                .padding(horizontal = 15.dp, vertical = 15.dp)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = time,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ChatInputField(
    inputMessage: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Input TextField untuk chat
        BasicTextField(
            value = inputMessage,
            onValueChange = onMessageChange,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary, // Warna teks
                fontSize = 14.sp // Ukuran font
            ),
            maxLines = 5,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
                .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(20.dp))
                .heightIn(min = 40.dp)
                .padding(horizontal = 15.dp)
                .weight(1f),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (inputMessage.isEmpty()) {
                        // Placeholder jika pesan kosong
                        Text(
                            text = "Type a message...",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                            fontSize = 14.sp
                        )
                    }
                    innerTextField() // Tempatkan inner text field di atas
                }
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        // Ikon kirim
        IconButton(
            onClick = onSendMessage,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary) // Warna ikon kirim
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send Message",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatPreview() {

}