<template>
    <div class="container">
        <h1>Ваши плейлисты</h1>
        <div class="card-group">
            <div class="playlists-list" v-bind:key="playlist.id" v-for="playlist in playlists">
                <PlaylistItem  v-bind:playlist="playlist"/>
            </div>
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
                playlists: []
            }
        },
        created() {
            axios.get("/api/playlists")
                .then(res => this.playlists = res.data)
                .catch(err => console.log(err))
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
