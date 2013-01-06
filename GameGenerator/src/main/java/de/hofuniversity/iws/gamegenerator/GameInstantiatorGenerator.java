/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.gamegenerator;

import java.io.PrintWriter;
import java.util.*;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

/**
 *
 * @author some
 */
public class GameInstantiatorGenerator extends Generator {
    
    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        TypeOracle oracle = context.getTypeOracle();
        
        JClassType instantiableType = oracle.findType("de.hofuniversity.iws.client.playn.PhysicGame");
        
        List<JClassType> clazzes = new ArrayList<JClassType>();
        for (JClassType classType : oracle.getTypes()) {
            if (!classType.equals(instantiableType) && classType.isAssignableTo(instantiableType)) {
                clazzes.add(classType);
            }
        }
        
        final String genPackageName = "de.hofuniversity.iws.client.play";
        final String genClassName = "GameInstantiatorImpl";
        
        ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);
        composer.addImplementedInterface("de.hofuniversity.iws.client.playn.GameInstantiator");
        composer.addImport("de.hofuniversity.iws.client.playn.PhysicGame");
        
        PrintWriter printWriter = context.tryCreate(logger, genPackageName, genClassName);
        
        if (printWriter != null) {
            SourceWriter sourceWriter = composer.createSourceWriter(context, printWriter);
            sourceWriter.println("GameInstantiatorImpl( ) {");
            sourceWriter.println("}");
            
            printFactoryMethod(clazzes, sourceWriter);
            
            sourceWriter.commit(logger);
        }
        return composer.getCreatedClassName();
    }
    
    private void printFactoryMethod(List<JClassType> clazzes, SourceWriter sourceWriter) {
        sourceWriter.println();
        
        sourceWriter.println("public PhysicGame createGame(String clazz) {");
        
        for (JClassType classType : clazzes) {
            if (classType.isAbstract()) {
                continue;
            }
            
            sourceWriter.println();
            sourceWriter.indent();
            sourceWriter.println("if (\"" + classType.getQualifiedSourceName() + "\".equals(clazz)) {");
            sourceWriter.indent();
            sourceWriter.println("return new " + classType.getQualifiedSourceName() + "( );");
            sourceWriter.outdent();
            sourceWriter.println("}");
            sourceWriter.outdent();
            sourceWriter.println();
        }
        sourceWriter.indent();
        sourceWriter.println("return null;");
        sourceWriter.outdent();
        sourceWriter.println();
        sourceWriter.println("}");
        sourceWriter.outdent();
        sourceWriter.println();
    }
}
