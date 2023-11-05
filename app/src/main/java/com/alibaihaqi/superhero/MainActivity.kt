package com.alibaihaqi.superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alibaihaqi.superhero.data.HeroesRepository
import com.alibaihaqi.superhero.model.Hero
import com.alibaihaqi.superhero.ui.theme.SuperheroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            SuperHeroAppBar()
        }
    ) {
        it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) {
                SuperHeroCard(
                    hero = it,
                    modifier = modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
                Row {
                    Text(
                        text = "Superheroes",
                        style = MaterialTheme.typography.displayLarge
                    )
                }
        },
        modifier = modifier
    )
}

@Composable
fun SuperHeroCard(hero: Hero,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
    ) {
        Row(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .padding(top = 16.dp, bottom = 16.dp)
                .height(72.dp)
                .wrapContentSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(
                modifier = Modifier
                    .size(72.dp, 72.dp)
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = stringResource(id = hero.nameRes),
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.small)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun SuperheroPreview() {
    SuperheroTheme(darkTheme = false) {
        SuperHeroApp()
    }
}