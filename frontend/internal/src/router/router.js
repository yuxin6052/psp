import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: 'Login - 登录'
    },
    component: () => import('@/views/login.vue')
};

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@//views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};


export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: Main,
    children: [
        { path: 'home', title:   'home', name: 'home_index', component: () => import('@/views/home-admin/home.vue') },
        { path: 'ownspace', title: 'Personal', name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
        { path: 'message', title: 'Message Center', name: 'message_index', component: () => import('@/views/message/message.vue') },

        { path: 'game-add', title: '游戏新增', name: 'game-add', icon: 'compose', component: () => import('@/views/game/game-add.vue') },
        { path: 'game-edit/:id', title: '游戏编辑', name: 'game-edit', icon: 'compose', component: () => import('@/views/game/game-edit.vue') },
        { path: 'game-config/:id', title: '游戏配置', name: 'game-config', icon: 'compose', component: () => import('@/views/game/game-config.vue') },
        { path: 'waybill-track/:status/:shipmentId', title: '运单跟踪', name: 'waybill-track', icon: 'compose', component: () => import('@/views/waybill-manage/waybill-track/waybill-track.vue') }
    ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
    {
        path: '/home',
        icon: 'android-checkbox',
        name: 'home',
        title: 'Home',
        component: Main,
        children: [
            { path: 'home', title: 'Home', name: 'home_index', icon: 'compose', component: () => import('@/views/home-admin/home.vue') },
            

        ]
    },
    {
        path: '/tenant',
        icon: 'android-checkbox',
        name: 'tenant',
        title: 'Tenant Mgnt',
        component: Main,
        children: [
            { path: 'tenant-list', title: 'Tenant Mgnt', name: 'tenant-list', icon: 'compose', component: () => import('@/views/tenant-manage/tenant-list/tenant-list.vue') },
            

        ]
    },
    {
        path: '/user',
        icon: 'android-checkbox',
        name: 'user',
        title: 'User Mgnt',
        component: Main,
        children: [
            { path: 'user-list', title: 'User Mgnt', name: 'user-list', icon: 'compose', component: () => import('@/views/user-manage/user-list/user-list.vue') },
        ]
    },

    {
        path: '/enterprise',
        icon: 'android-checkbox',
        name: 'enterprise',
        title: 'enterprise list',
        component: Main,
        children: [
            { path: 'enterprise-list', title: 'enterprise list', name: 'enterprise-list', icon: 'compose', component: () => import('@/views/enterprise/eneterprise-list.vue') },


        ]
    }
    // {
    //     path: '/',
    //     icon: 'android-checkbox',
    //     name: 'game',
    //     title: '游戏管理',
    //     component: Main,
    //     children: [
    //         { path: 'game-list', title: '游戏列表', name: 'game-list', icon: 'compose', component: () => import('@/views/game/game-list.vue') }
    //     ]
    // },
    // {
    //     path: '/user',
    //     icon: 'android-checkbox',
    //     name: 'user',
    //     title: '用户管理',
    //     component: Main,
    //     children: [
    //         { path: 'users', title: '用户列表', name: 'users', icon: 'compose', component: () => import('@/views/user/users.vue') }
    //     ]
    // }
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    otherRouter,
    locking,
    ...appRouter,
    page500,
    page403,
    page404
];
