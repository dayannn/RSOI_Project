<template>
    <div class = "song-item shadow" style="margin-bottom: 10px; padding-top: 10px">
        <b-button class="btn btn-m shadowed-button" style="float: right; margin-right: 10px" @click="deleteSong">X</b-button>
        <strong style="padding-left: 20px">{{song.artistName}}</strong> - {{song.name}}<br/>
        <audio
                controls
                :src="basic_url + this.song.filePath">
            Your browser does not support the
            <code>audio</code> element.
        </audio>
    </div>
</template>

<script>
    import axios from 'axios';
    export default{
        name: "SongItem",
        props: ["song"],
        methods:{
            deleteSong(){
                this.$emit('del-song-from-playlist', this.song.id)
            }
        },
        data() {
            return {
                basic_url: "http://localhost:8088/audiosrc"
            }
        }
    }
</script>

<style scoped>
    .song-item{
        background: #f4f4f4;
        padding: 5px;
        border-bottom: 1px #ccc solid;
        text-align: left;
        width: 70%;
        margin: auto;
    }

    .shadowed-button {
        box-shadow: 0 4px 5px 0 rgba(0, 0, 0, .14), 0 1px 10px 0 rgba(0, 0, 0, .12), 0 2px 4px -1px rgba(0, 0, 0, .2)
    }
    .shadowed-button:hover{
        box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px rgba(0, 0, 0, .2), 0 1px 5px 0 rgba(0, 0, 0, .12)
    }

</style>
