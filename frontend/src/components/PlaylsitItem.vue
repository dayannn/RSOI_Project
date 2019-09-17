<template>
    <div :location="playlist">
        <div class="place shadow">
            <router-link :to="'playlist/' + this.playlist.id" width="235" height="300">
                <img src="../assets/playlist.svg" alt="cover" />
            </router-link>
            <slot></slot>
            <p>{{ playlist.name}}</p>
        </div>
    </div>
</template>

<script>
    import StarRating from 'vue-dynamic-star-rating';
    import truncate from './truncate';
    export default{
        name: "PlaylistItem",
        components: {truncate},
        props: ["playlist"]

    }
</script>

<!--TODO make it a global style-->
<style scoped>
    .shadowed-button {
        box-shadow: 0 4px 5px 0 rgba(0, 0, 0, .14), 0 1px 10px 0 rgba(0, 0, 0, .12), 0 2px 4px -1px rgba(0, 0, 0, .2)
    }
    .shadowed-button:hover{
        box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px rgba(0, 0, 0, .2), 0 1px 5px 0 rgba(0, 0, 0, .12)
    }

    .place {
        display: flex;
        flex-direction: column;
        width: 250px;
        height: 280px;
        justify-content: center;
        background: white;
        border: 1px solid #ddd;
        padding: 20px 20px;
    }
    .place h2 {
        margin: 0;
        text-align: center;
    }
    .place img {
        margin: 10px;
        align-self: center;
    }

    .place p{
        font-family: "Segoe UI", serif;
        font-weight: bold;
    }
</style>
