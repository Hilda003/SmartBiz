package com.example.smartbiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.smartbiz.R

@Composable
fun ProfileImage() {
    Column() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(
                "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80"
            ).crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .border(1.dp, color = colorResource(id = R.color.teal_700), CircleShape)
                .clip(CircleShape)

        )
    }

}

@Composable
fun DetailProfile(title: String, icon: Int){
//    val context = LocalContext.current
//
//        Row() {
//            Text(
//                text = title,
//                color = colorResource(id = R.color.brown),
//                fontSize = 16.sp,
//                modifier = Modifier.padding(top =9.dp)
//
//            )
//        }


}

@Preview
@Composable
fun ProfileImagePreview() {
    DetailProfile(title = "Profile", icon = R.drawable.person)
}