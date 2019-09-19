<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container">
        <h1>Ваши плейлисты</h1>
        <div class="card-group">
            <div class="playlists-list" v-bind:key="playlist.id" v-for="playlist in playlists">
                <PlaylistItem  v-bind:playlist="playlist"/>
            </div>
            <b-button class="btn btn-m shadowed-button shadow"
                      style="position:fixed; right:0; bottom:0; margin: 10px"
                      @click="showModal=true">Добавить плейлист</b-button>

            <b-modal
                    v-model="showModal"
                    title="Добавить плейлист"
                    hide-footer
                    centered
            >
                <b-form-group
                        label="Имя плейлиста:"
                        label-for="input0">
                    <b-form-input
                            id="input0"
                            type="text"
                            class="shadow-sm"
                            v-model="playlist.name"
                            required
                            placeholder="Введите название плейлиста">
                    </b-form-input>
                </b-form-group>
                <template v-slot:modal-footer>
                    <div class="w-100">
                        <b-button
                                variant="primary"
                                size="sm"
                                class="float-right shadowed-button"
                                @click="addPlaylist"
                                type="submit"
                        >
                            Создать
                        </b-button>
                    </div>
                </template>
            </b-modal>
        </div>
    </div>
</template>

<script>
    import PlaylistItem from '../components/PlaylsitItem';
    import axios from 'axios';
    export default {
        name: "playlists",
        components: {PlaylistItem},
        data() {
            // return{
            //     books:[{id:"1", name:"book", description:"blablalba"}]
            // }
            return {
                playlists: [],
                showModal: false,
                playlist:{
                    name: ""
                }
            }
        },
        created() {
            this.updateData()
        },
        methods:{
            updateData(){
               axios.get("/api/playlists")
                    .then(res => this.playlists = res.data)
                    .catch(err => console.log(err))
            },
            addPlaylist(){
                this.showModal=false;
                if (this.playlist.name === ""){
                    return;
                }
                axios.post("/api/playlists", this.playlist)
                    .then(this.updateData)
                    .catch(err => console.log(err))
            }
        }
    }

</script>

<style scoped>
    .playlists-list{
        width: 60%;
        margin: auto;
        padding: 8px;
    }

    .card-group{
        display: grid;
        /*grid-template-columns: repeat(4, 1fr);*/
        grid-template-columns: repeat(auto-fill, 300px);
        grid-gap: 10px;
        /*display: flex;*/
        justify-content: center;
    }


</style>
