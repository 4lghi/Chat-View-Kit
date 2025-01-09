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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chat_republik.ui.theme.ChatrepublikTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
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


data class ChatMessage(
    val isSender: Boolean,
    val username: String,
    val text: String,
    val time: String
)


@Composable
fun Chat() {
    var inputMessage by remember { mutableStateOf("") }
//    val messages = remember {mutableStateListOf<ChatMessage>()}
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                isSender = true,
                username = "You",
                text = "Hi, how are you?",
                time = "24-01-2025 10:00"
            ),
            ChatMessage(
                isSender = false,
                username = "Alice",
                text = "I'm good, thanks! How about you?",
                time = "24-01-2025 10:01"
            ),
            ChatMessage(
                isSender = true,
                username = "You",
                text = "I'm doing well. What's up?",
                time = "24-01-2025 10:02"
            ),
            ChatMessage(
                isSender = false,
                username = "Alice",
                text = "Just checking in. Are we still on for the meeting later?",
                time = "24-01-2025 10:05"
            ),
            ChatMessage(
                isSender = true,
                username = "You",
                text = "Yes, let's meet at 2 PM.",
                time = "24-01-2025 10:06"
            ),
            ChatMessage(
                isSender = false,
                username = "Bob",
                text = "Hey, can you send me the report?",
                time = "25-01-2025 09:30"
            ),
            ChatMessage(
                isSender = true,
                username = "You",
                text = "Sure, give me a minute.",
                time = "25-01-2025 09:32"
            ),
            ChatMessage(
                isSender = false,
                username = "Bob",
                text = "Thanks! Appreciate it.",
                time = "25-01-2025 09:35"
            ),
            ChatMessage(
                isSender = true,
                username = "You",
                text = "No problem!",
                time = "25-01-2025 09:37"
            ),
            ChatMessage(
                isSender = false,
                username = "Alice",
                text = "How's everything going on your side?",
                time = "26-01-2025 11:00"
            ),
            ChatMessage(
                isSender = true,
                username = "You",
                text = "Everything's good. Just been busy with work.",
                time = "26-01-2025 11:05"
            )
        )
    }

    val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
    var dateBefore by remember { mutableStateOf("") }

    var isMessageReceived by remember { mutableStateOf(false) }

//    username user
    val username = "user"
//    username sender
    val senderUsername = "user"


    //    Load chat history dari server atau database
    LaunchedEffect(Unit) {

    }

//        jika ada pesan masuk
    if (isMessageReceived) {
        messages.add(ChatMessage(false, senderUsername, "Halooo", currentTime))
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
                text = username,
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
                modifier = Modifier.weight(1f)
            ) {
                items(messages) { message ->
                    val timeFormatted = try {
                        LocalDateTime.parse(message.time, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                            .format(DateTimeFormatter.ofPattern("HH:mm"))
                    } catch (e: DateTimeParseException) {
                        Log.e("ChatApp", "Parsing error for time: ${e.message}")
                        ""
                    }

                    val dateFormatted = try {
                        LocalDateTime.parse(message.time, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    } catch (e: DateTimeParseException) {
                        Log.e("ChatApp", "Parsing error for date: ${e.message}")
                        ""
                    }

                    // Jika tanggal pesan berubah, tambahkan header tanggal baru
                    if (dateFormatted != dateBefore) {
                        dateBefore = dateFormatted

                        Row {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .wrapContentSize(Alignment.Center) // Agar Box mengikuti lebar konten
                            ) {
                                Text(
                                    text = dateFormatted,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.primary) // Background pada Text saja
                                        .padding(horizontal = 10.dp, vertical = 8.dp), // Padding sekitar Text
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }


                    // Tampilkan pesan sesuai dengan siapa yang mengirim
                    if (message.isSender) {
                        SenderMessage(text = message.text, time = timeFormatted)
                    } else {
                        ReceiverMessage(text = message.text, time = timeFormatted)
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
                        messages.add(ChatMessage(true, username, inputMessage, currentTime))
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