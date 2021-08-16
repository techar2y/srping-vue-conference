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

let router = new Router({
    mode: "history",
    routes: [
        {
            path: "/presentation",
            alias: "/presentations",
            name: "presentations-list",
            component: () => import("./components/presentation/PresentationsList")
        },

        {
            path: "/presentations/:id",
            name: "presentation-edit",
            component: () => import("./components/presentation/Presentation")
        },

        {
            path: "/addPresentation",
            name: "presentation-add",
            component: () => import("./components/presentation/AddPresentation")
        },

        {
            path: "/users",
            name: "users-list",
            component: () => import("./components/user/UsersList")
        },

        {
            path: "/users/:id",
            name: "user-edit",
            component: () => import("./components/user/User")
        },

        {
            path: "/addUser",
            name: "user-add",
            component: () => import("./components/user/AddUser")
        },


        {
            path: "/schedules",
            name: "schedules-list",
            component: () => import("./components/schedule/SchedulesList")
        },

        {
            path: "/login",
            name: "login",
            component: () => import("./components/auth/Login")
        },

        {
            path: "/register",
            name: "register",
            component: () => import("./components/auth/Register")
        }
    ]

});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register', '/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});

export default router;