/*
  * Copyright (C) 2012 Daniel Heinrich
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.gamegenerator;

import java.io.PrintWriter;
import java.util.*;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.*;
import com.google.gwt.user.rebind.*;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
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
