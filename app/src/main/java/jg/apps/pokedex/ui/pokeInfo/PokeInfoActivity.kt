package jg.apps.pokedex.ui.pokeInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import jg.apps.pokedex.R
import jg.apps.pokedex.viewmodel.PokeInfoViewModel
import kotlinx.android.synthetic.main.activity_pokeinfo.*

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)


        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        initUI()
    }

    private fun initUI(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this) { pokemon ->
            nameTextView.text = pokemon.name
            heightText.text = "Altura: ${pokemon.height / 10.0}m"
            weightText.text = "Peso: ${pokemon.weight / 10.0}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        }
    }
}