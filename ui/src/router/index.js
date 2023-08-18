import { createWebHistory, createRouter } from "vue-router"
import Dashboard from "@/components/Dashboard.vue";
import Record from "@/components/Record.vue";

export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/dashboard",
            name: "Dashboard",
            component: Dashboard
        },
        {
            path: "/record",
            name: "Record",
            component: Record
        }
    ],
    // mode: history
})

