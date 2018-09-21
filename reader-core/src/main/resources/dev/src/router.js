import Login from './Login.vue'
import App from './App.vue'
import Reader from './Reader.vue'
import Stock from './views/Stock.vue'
import Flow from './views/Flow.vue'
import LastFlow from './views/LastFlow.vue'

export default [
    {path: '/', component: Login},
    {
        path: '/',
        component: Reader,
        children: [
            {path: '/reader', component: Stock, props: {isReader: true}},
        ]
    },
    {
        path: '/app',
        component: App,
        children: [
            {path: '/app/stock', component: Stock, props: {isReader: false}},
            {path: '/app/flow', component: Flow},
            {path: '/app/lastFlow', component: LastFlow},
        ]
    }
]