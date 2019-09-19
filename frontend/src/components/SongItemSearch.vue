<template>
    <div class = "song-item shadow"
         v-on:mouseover="dropdownActive = true"
         v-on:mouseout="dropdownActive = false"
         style="margin-bottom: 10px; padding-top: 10px">
        <strong style="padding-left: 20px">{{song.artistName}}</strong> - {{song.name}}<br/>
        <b-row>
            <b-col>
                <audio
                        controls
                        :src="basic_url + this.song.filePath">
                    Your browser does not support the
                    <code>audio</code> element.
                </audio>
            </b-col>
            <b-col>
                <b-dropdown :text="dropdownText" v-show="dropdownActive" style="float: right; margin-right: 30px">
                    <b-dropdown-item v-bind:key="playlist.id" v-for="playlist in this.$parent.playlists">{{playlist.name}}</b-dropdown-item>
                    <!--<b-dropdown-item @click="selectedItem='Another action'">Another action</b-dropdown-item>-->
                    <!--<b-dropdown-item @click="selectedItem='Something else here'">Something else here</b-dropdown-item>-->
                </b-dropdown>
            </b-col>
        </b-row>

    </div>
</template>

<script>
    import axios from 'axios';
    export default{
        name: "SongItem",
        props: ["song"],
        methods:{
        },
        data() {
            return {
                basic_url: "http://localhost:8088/audiosrc",
                dropdownText: "Добавить в плейлист",
                dropdownActive: false
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

</style>
