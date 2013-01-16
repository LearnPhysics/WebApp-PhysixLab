/*
 * Copyright (C) 2013 Daniel Heinrich <dannynullzwo@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * (version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/> 
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301  USA.
 */
package de.hofuniversity.iws.server.oauth;

import java.text.StringCharacterIterator;
import com.google.common.base.Optional;
import de.hofuniversity.iws.server.oauth.accessors.FriendListAccessor;
import junit.framework.TestCase;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.scribe.model.Token;

import static org.mockito.Mockito.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@RunWith(Theories.class)
public class OAuthTest extends TestCase{
    @DataPoints Providers[] provider = Providers.values();
    
    @Theory
    public void testFriendListAccessor(Providers provider)
    {
        Optional<FriendListAccessor> optional = provider.getAccessor(FriendListAccessor.class);
        Assume.assumeTrue(optional.isPresent());
        
        FriendListAccessor accessor = optional.get();
        assertEquals(accessor.supportedProvider(), provider);
    }
}
