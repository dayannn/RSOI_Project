<template>
    <div class = "song-item shadow" style="margin-bottom: 10px">
        <b-button class="btn btn-info btn-sm shadowed-button" style="margin-right: 10px">Play</b-button>
        <b-button class="btn btn-info btn-sm shadowed-button" style="margin-right: 10px">Stop</b-button>
        <strong>{{song.artistName}}</strong> - {{song.name}}<br/>
    </div>
</template>

<script>
    export default{
        name: "SongItem",
        props: ["song"],
        methods:{
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
