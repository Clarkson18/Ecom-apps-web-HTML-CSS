/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.happysource_webapp.persistencia;

import com.mycompany.happysource_webapp.Interfaces.IAdministradoresDAO;
import com.mycompany.happysource_webapp.Interfaces.IUsuariosDAO;
import com.mycompany.happysource_webapp.dominio.AdminDTO;

/**
 *
 * @author vv094
 */
public class UsuariosDAO implements IUsuariosDAO, IAdministradoresDAO{
    @Override
    public AdminDTO loginAdmin(String user, String pass){
        return new AdminDTO();
    }
}
