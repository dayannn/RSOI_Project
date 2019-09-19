<template>
    <div style="padding-top: 75px; padding-bottom: 25px">
        <div class="card shadow" style="width: 70%; margin: auto; padding: 20px; margin-bottom: 20px; text-align: left">
            <h4 class="card-title"><b>Исполнитель «{{artist.name}}»</b></h4>
            <p style="">{{artist.info}}</p>
        </div>
        <h5>Альбомы исполнителя</h5>
        <div class="card-group">
            <div class="albums-list" v-bind:key="album.id" v-for="album in artist.albums">
                <AlbumItem v-bind:album="album"/>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import AlbumItem from '../components/AlbumItem';
    export default {
        name:"Artist",
        components:{AlbumItem},
        computed:{},
        methods: {
            updateData(){
                axios.get("/api/artist/" + this.$route.params.id)
                    .then(res => {
                        this.artist = res.data;
                    })
                    .catch(err => console.log(err));
            },
        },
        data(){
            return {
                artist: {
                    name: "",
                    albums: []
                },
            }
        },
        created() {
            this.updateData();
        }
    }

</script>

<style scoped>

    .albums-list{
        width: 60%;
        margin: auto;
        padding: 8px;
    }
</style>
