appService.service('PermissionService', ['$resource', '$q', function ($resource, $q) {
    var permission_resource = $resource('', {}, {
        has_app_permission: {
            method: 'GET',
            url: '/apps/:appId/permissions/:permissionType'
        },
        has_namespace_permission: {
            method: 'GET',
            url: '/apps/:appId/namespaces/:namespaceName/permissions/:permissionType'
        },
        has_root_permission:{
            method: 'GET',
            url: '/permissions/root'
        },
        get_namespace_role_users: {
            method: 'GET',
            url: '/apps/:appId/namespaces/:namespaceName/role_users'
        },
        assign_namespace_role_to_user: {
            method: 'POST',
            url: '/apps/:appId/namespaces/:namespaceName/roles/:roleType'
        },
        remove_namespace_role_from_user: {
            method: 'DELETE',
            url: '/apps/:appId/namespaces/:namespaceName/roles/:roleType?user=:user'
        },
        get_app_role_users: {
            method: 'GET',
            url: '/apps/:appId/role_users'    
        },
        assign_app_role_to_user: {
            method: 'POST',
            url: '/apps/:appId/roles/:roleType'
        },
        remove_app_role_from_user: {
            method: 'DELETE',
            url: '/apps/:appId/roles/:roleType?user=:user'
        }
    });

    function hasAppPermission(appId, permissionType) {
        var d = $q.defer();
        permission_resource.has_app_permission({
                                                   appId: appId,
                                                   permissionType: permissionType
                                               },
                                               function (result) {
                                                   d.resolve(result);
                                               }, function (result) {
                d.reject(result);
            });
        return d.promise;
    }

    function hasNamespacePermission(appId, namespaceName, permissionType) {
        var d = $q.defer();
        permission_resource.has_namespace_permission({
                                                         appId: appId,
                                                         namespaceName: namespaceName,
                                                         permissionType: permissionType
                                                     },
                                                     function (result) {
                                                         d.resolve(result);
                                                     }, function (result) {
                d.reject(result);
            });
        return d.promise;
    }

    function assignNamespaceRoleToUser(appId, namespaceName, roleType, user) {
        var d = $q.defer();
        permission_resource.assign_namespace_role_to_user({
                                                              appId: appId,
                                                              namespaceName: namespaceName,
                                                              roleType: roleType
                                                          }, user,
                                                          function (result) {
                                                              d.resolve(result);
                                                          }, function (result) {
                d.reject(result);
            });
        return d.promise;
    }

    function removeRoleFromUser(appId, namespaceName, roleType, user) {
        var d = $q.defer();
        permission_resource.remove_namespace_role_from_user({
                                                                appId: appId,
                                                                namespaceName: namespaceName,
                                                                roleType: roleType,
                                                                user: user
                                                            },
                                                            function (result) {
                                                                d.resolve(result);
                                                            }, function (result) {
                d.reject(result);
            });
        return d.promise;
    }

    return {
        has_create_namespace_permission: function (appId) {
            return hasAppPermission(appId, 'CreateNamespace');
        },
        has_create_cluster_permission: function (appId) {
            return hasAppPermission(appId, 'CreateCluster');
        },
        has_assign_user_permission: function (appId) {
            return hasAppPermission(appId, 'AssignRole');
        },
        has_modify_namespace_permission: function (appId, namespaceName) {
            return hasNamespacePermission(appId, namespaceName, 'ModifyNamespace');
        },
        has_release_namespace_permission: function (appId, namespaceName) {
            return hasNamespacePermission(appId, namespaceName, 'ReleaseNamespace');
        },
        has_root_permission: function () {
            var d = $q.defer();
            permission_resource.has_root_permission({ },
                                                         function (result) {
                                                             d.resolve(result);
                                                         }, function (result) {
                    d.reject(result);
                });
            return d.promise;    
            
        },
        assign_modify_namespace_role: function (appId, namespaceName, user) {
            return assignNamespaceRoleToUser(appId, namespaceName, 'ModifyNamespace', user);
        },
        assign_release_namespace_role: function (appId, namespaceName, user) {
            return assignNamespaceRoleToUser(appId, namespaceName, 'ReleaseNamespace', user);
        },
        remove_modify_namespace_role: function (appId, namespaceName, user) {
            return removeRoleFromUser(appId, namespaceName, 'ModifyNamespace', user);
        },
        remove_release_namespace_role: function (appId, namespaceName, user) {
            return removeRoleFromUser(appId, namespaceName, 'ReleaseNamespace', user);
        },
        get_namespace_role_users: function (appId, namespaceName) {
            var d = $q.defer();
            permission_resource.get_namespace_role_users({
                                                              appId: appId,
                                                              namespaceName: namespaceName,
                                                          },
                                                         function (result) {
                                                              d.resolve(result);
                                                          }, function (result) {
                    d.reject(result);
                });
            return d.promise;
        },
        get_app_role_users: function (appId) {
            var d = $q.defer();
            permission_resource.get_app_role_users({
                                                        appId: appId
                                                    },
                                                   function (result) {
                                                        d.resolve(result);
                                                    }, function (result) {
                    d.reject(result);
                });
            return d.promise;    
        },
        assign_master_role: function (appId, user) {
            var d = $q.defer();
            permission_resource.assign_app_role_to_user({
                                                            appId: appId,
                                                            roleType: 'Master'
                                                        }, user,
                                                        function (result) {
                                                            d.resolve(result);
                                                        }, function (result) {
                    d.reject(result);
                });
            return d.promise;
        },
        remove_master_role: function (appId, user) {
            var d = $q.defer();
            permission_resource.remove_app_role_from_user({
                                                              appId: appId,
                                                              roleType: 'Master',
                                                              user: user
                                                          },
                                                          function (result) {
                                                              d.resolve(result);
                                                          }, function (result) {
                    d.reject(result);
                });
            return d.promise;
        }
    }
}]);
