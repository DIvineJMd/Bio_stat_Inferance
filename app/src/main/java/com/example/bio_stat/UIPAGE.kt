package com.example.bio_stat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.UriPermission
import android.net.Uri
import android.provider.DocumentsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.io.InputStream
import java.io.InputStreamReader
import com.opencsv.CSVReader
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
class UIPAGE {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
    @Composable
    fun Questionask() {
        var visible by remember {
            mutableStateOf(true)
        }
        val context = LocalContext.current
        var currentNode by remember { mutableIntStateOf(0) }
        var csvDataList by remember { mutableStateOf<Map<String, List<Any>>?>(null) }
        val filePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                csvDataList = readCsvFromUri(context, uri)
            }
        }
        Scaffold(

            topBar = {
                CenterAlignedTopAppBar(

                    navigationIcon = {
                        Card(
                            modifier = Modifier.padding(5.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                contentColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            IconButton(onClick = {
                                filePickerLauncher.launch("*/*")

                            }) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircle,
                                    contentDescription = "Localized description",
                                )
                            }
                        }
                    },
                    actions = {
                        Card(
                            modifier = Modifier.padding(5.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                contentColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            IconButton(onClick = { currentNode = 0 }) {
                                Icon(
                                    imageVector = Icons.Filled.Refresh,
                                    contentDescription = "Localized description",

                                    )
                            }
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.LightGray,
                    ),
                    title = {

                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "StatInfer",
                            color = MaterialTheme.colorScheme.inversePrimary,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )

                    }
                )
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF), // Start color
                                MaterialTheme.colorScheme.primaryContainer // End color
                            )
                        )
                    )
            ) {
                val flowdata = FlowChat
                var sol by remember { mutableStateOf(-1) }
                val scope = rememberCoroutineScope()
                Column(
                    Modifier
                        .padding(vertical = 25.dp)
//                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                )
                {
                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        visible = visible
                    ) {
                        // Ask elements
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            )
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = flowdata.flowchart[currentNode].question,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 20.sp,
                            )
                        }

                    }
                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        visible = flowdata.flowchart[currentNode].Extra != null
                    ) {
                        // Ask elements
                        Card(
                            modifier = Modifier
                                .padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            )
                        ) {
                            Row(modifier = Modifier.padding(5.dp)) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Localized description",

                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(25.dp)
                                )
                                flowdata.flowchart[currentNode].Extra?.let { it1 ->
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp),
                                        text = it1,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold

                                    )
                                }
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    {
                        Button(
                            onClick = {
                                scope.launch {

                                    if (flowdata.flowchart[currentNode].yesIndex != -1) {
                                        visible = false
                                        kotlinx.coroutines.delay(500)
                                        currentNode = flowdata.flowchart[currentNode].yesIndex
                                        visible = true
                                        sol = -1
                                    } else {
                                        sol = 0
                                    }


                                }


                            },
                            modifier = Modifier.padding(5.dp)
                        ) {
                            flowdata.flowchart[currentNode].yesButton?.let { it1 -> Text(text = it1) }
                        }

                        Button(
                            onClick = {
                                scope.launch {

                                    if (flowdata.flowchart[currentNode].noIndex != -1) {
                                        visible = false
                                        kotlinx.coroutines.delay(500)
                                        currentNode = flowdata.flowchart[currentNode].noIndex
                                        visible = true
                                        sol = -1
                                    } else {
                                        sol = 1
                                    }

                                }
                            },
                            modifier = Modifier.padding(5.dp)
                        ) {
                            flowdata.flowchart[currentNode].noButton?.let { it1 -> Text(text = it1) }
                        }

                        if (flowdata.flowchart[currentNode].threeChoice != -1
                        ) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        if(flowdata.flowchart[currentNode].threeChoice != -2){


                                            visible = false
                                            kotlinx.coroutines.delay(500)
                                            currentNode =
                                                flowdata.flowchart[currentNode].threeChoice!!
                                            visible = true
                                            sol = -1}else{
                                            sol=2
                                        }
                                    }


                                },
                                modifier = Modifier.padding(5.dp)
                            ) {
                                flowdata.flowchart[currentNode].threeButton?.let { it1 -> Text(text = it1) }
                            }
                        }
                    }
                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        visible = sol != -1
                    )
                    {
                        // Ask elements
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            )
                        ) {
                            if (sol == 0) {
                                flowdata.flowchart[currentNode].yesAction?.let { it1 ->
                                    Text(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .align(Alignment.CenterHorizontally),
                                        text = it1,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 18.sp,
                                    )
                                }
                            } else {
                                if(sol!=2){
                                    flowdata.flowchart[currentNode].noAction?.let { it1 ->
                                        Text(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .align(Alignment.CenterHorizontally),
                                            text = it1,
                                            color = MaterialTheme.colorScheme.tertiary,
                                            fontSize = 18.sp,
                                        )
                                    }
                                }else{
                                    flowdata.flowchart[currentNode].threeAction?.let { it1 ->
                                        Text(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .align(Alignment.CenterHorizontally),
                                            text = it1,
                                            color = MaterialTheme.colorScheme.tertiary,
                                            fontSize = 18.sp,
                                        )
                                    }
                                }
                            }
                        }

                    }


                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        visible =csvDataList != null
                    ){
                        LazyRow {
                            items(csvDataList!!.toList()) { (fieldName, rowData) ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(text = fieldName, fontWeight = FontWeight.Bold, color =MaterialTheme.colorScheme.primaryContainer )
                                    rowData.forEachIndexed { index, data ->
                                        Text(text = "$data", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    fun readCsvFromUri(context: Context, uri: Uri): Map<String, List<Any>>? {
        val inputStream = context.contentResolver.openInputStream(uri)
        return inputStream?.use { stream ->
            val reader = InputStreamReader(stream)
            val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

            var isFirstLine = true
            var fieldNames: List<String>? = null
            val csvDataMap = mutableMapOf<String, MutableList<Any>>()

            for (record in csvParser) {
                if (isFirstLine) {
                    isFirstLine = false
                    fieldNames = record.toList()
                    // Initialize lists for each column name
                    fieldNames?.forEach { columnName ->
                        csvDataMap[columnName] = mutableListOf()
                    }
                    continue
                }

                if (fieldNames != null) {
                    // Process each field according to its field name
                    fieldNames.forEachIndexed { index, columnName ->
                        val fieldValue = record.get(index)
                        if (fieldValue != null) {
                            csvDataMap[columnName]?.add(fieldValue)
                        }
                    }
                }
            }

            csvParser.close()
            csvDataMap
        }
    }







    @Preview
    @Composable
    fun PreviewQuestionask(){
        Questionask()
    }
}
