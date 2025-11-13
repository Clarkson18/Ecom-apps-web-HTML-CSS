/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.happysource_webapp.Interfaces;

import com.mycompany.happysource_webapp.dominio.AdminDTO;

/**
 *
 * @author vv094
 */
public interface IAdministradoresDAO {
    public AdminDTO loginAdmin(String user, String pass);
}
