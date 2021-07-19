/*import { createWebHistory, createRouter } from "vue-router";

const routes =  [
    {
        path: "/schedules",
        alias: "/schedules",
        name: "schedules",
        component: () => import("./components/schedule/SchedulesList")
    },
    {
        path: "/schedules/:id",
        name: "schedule-details",
        component: () => import("./components/schedule/Schedule")
    },
    {
        path: "/schedules/addSchedule",
        name: "add-schedule",
        component: () => import("./components/schedule/AddSchedule")
    },
    {
        path: "/users",
        alias: "/users",
        name: "users",
        component: () => import("./components/user/UsersList")
    },
    {
        path: "/users/:id",
        name: "user-details",
        component: () => import("./components/user/User")
    },
    {
        path: "/users/addUser",
        name: "add-user",
        component: () => import("./components/user/AddUser")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;*/

import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/schedules",
            alias: "/schedules",
            name: "schedules",
            component: () => import("./components/schedule/SchedulesList")
        },
        {
            path: "/schedules/:id",
            name: "schedule-details",
            component: () => import("./components/schedule/Schedule")
        },
        {
            path: "/addSchedule",
            name: "addSchedule",
            component: () => import("./components/schedule/AddSchedule")
        },
        {
            path: "/users",
            alias: "/users",
            name: "users",
            component: () => import("./components/user/UsersList")
        },
        {
            path: "/users/:id",
            name: "user-details",
            component: () => import("./components/user/User")
        },
        {
            path: "/addUser",
            name: "addUser",
            component: () => import("./components/user/AddUser")
        },


        {
            path: "/",
            alias: "/tutorials",
            name: "tutorials",
            component: () => import("./components/tutorials/TutorialsList")
        },
        {
            path: "/tutorials/:id",
            name: "tutorial-details",
            component: () => import("./components/tutorials/Tutorial")
        },
        {
            path: "/add",
            name: "add",
            component: () => import("./components/tutorials/AddTutorial")
        }
    ]
});